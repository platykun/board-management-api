package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.*;
import boardmanagement.api.demo.manage.bean.CreateRoomRequestBean;
import boardmanagement.api.demo.manage.bean.ResultRequestBean;
import boardmanagement.api.demo.manage.bean.StatusResponseBean;
import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.dto.ResultDto;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.UserStatusService;
import boardmanagement.api.demo.manage.service.base.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * ユーザコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final
    UserService userService;

    /**
     * ルームサービスクラス.
     */
    private final
    RoomService roomService;

    /**
     * チェックインサービスクラス.
     */
    private final
    CheckInService checkInService;

    /**
     * 結果情報サービスクラス.
     */
    private final
    ResultService resultService;

    private final
    UserStatusService userStatusService;

    /**
     * ルーム参加サービスクラス.
     */
    private final JoinRoomService joinRoomService;

    @Autowired
    public UserController(UserService userService, RoomService roomService, JoinRoomService joinRoomService, CheckInService checkInService, ResultService resultService,
                          UserStatusService userStatusService) {
        this.userService = userService;
        this.roomService = roomService;
        this.joinRoomService = joinRoomService;
        this.checkInService = checkInService;
        this.resultService = resultService;
        this.userStatusService = userStatusService;
    }

    /**
     * ルームを作成する.
     * @param createRoomRequestBean ルーム情報
     * @return 作成結果
     */
    @PutMapping(path="create_room")
    SuccessBean<RoomEntityBean> createRoom(@RequestBody CreateRoomRequestBean createRoomRequestBean){

        RegisterRoomDto registerRoomDto = new RegisterRoomDto();
        BeanUtils.copyProperties(createRoomRequestBean, registerRoomDto);
        RoomEntityBean createdRoom = roomService.register(registerRoomDto);

        return new SuccessBean<>(createdRoom);
    }

    /**
     * ルームに参加する.
     * @param roomId ルームID
     * @return 参加後のルーム情報
     */
    @PutMapping("join/{roomId:^[a-z0-9]+$}")
    SuccessBean<JoinRoomEntityBean> joinRoom(@PathVariable String roomId, Principal principal) {
        // idのユーザをroomIdのルームへ登録させる.
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        int userIdNum = loginUser.get().getId();
        int roomIdNum = Integer.parseInt(roomId);
        RegisterJoinRoomDto registerJoinRoomDto = new RegisterJoinRoomDto(userIdNum, roomIdNum, false);

        JoinRoomEntityBean result = joinRoomService.register(registerJoinRoomDto);

        return new SuccessBean<>(result);
    }

    /**
     * チェックインする.
     *
     * @param placeName 場所名
     * @return チェックイン済情報
     */
    @PutMapping(path="checkin/{placeName}")
    SuccessBean<CheckInEntityBean> checkin(@PathVariable String placeName){
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        CheckInEntityBean registerdBean = checkInService.register(loginUser.get().getId(), placeName);

        return new SuccessBean<>(registerdBean);
    }

    /**
     * 結果を記録する.
     *
     * @param resultRequestBean 結果登録情報
     * @return 登録結果
     */
    @PutMapping(path="result")
    SuccessBean<ResultEntityBean> result(@RequestBody ResultRequestBean resultRequestBean){
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        // TODO: ログインユーザ情報を取得してDtoに詰める作業はServiceの責務にする
        ResultDto resultDto = resultRequestBean.toResultDto();
        resultDto.setUserId(loginUser.get().getId());

        ResultEntityBean bean = resultService.register(resultDto);

        return new SuccessBean<>(bean);
    }

    /**
     * ログインユーザのステータス情報を取得する.
     * @return ログインユーザのステータス情報
     */
    @GetMapping(path="status")
    SuccessBean<StatusResponseBean> status(){
        UserStatusResponseDto dto = userStatusService.getLoginUserStatus();
        return new SuccessBean<>(new StatusResponseBean(dto));
    }

    /**
     * ログインユーザの参加履歴を取得する.
     * @param page ページング
     * @return 参加履歴
     */
    @GetMapping(path="history/join/{page:^[a-z0-9]+$}")
    public SuccessBean<List<JoinRoomEntityBean>> joinHistory(@PathVariable int page) {
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        List<JoinRoomEntityBean> joinHistory = joinRoomService.findJoinRoomByUserId(loginUser.get().getId(), page);

        return new SuccessBean<>(joinHistory);
    }

    /**
     * ログインユーザのチェックイン履歴を取得する.
     * @param page ページング
     * @return チェックイン履歴
     */
    @GetMapping(path="history/checkin/{page:^[a-z0-9]+$}")
    public SuccessBean<List<CheckInEntityBean>> checkInHistory(@PathVariable int page) {
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        List<CheckInEntityBean> checkInHistory = checkInService.findCheckInByUserId(loginUser.get().getId(), page);

        return new SuccessBean<>(checkInHistory);
    }

    /**
     * ログインユーザの結果履歴を取得する.
     * @param page ページング
     * @return 結果履歴
     */
    @GetMapping(path="history/result/{page:^[a-z0-9]+$}")
    public SuccessBean<List<ResultEntityBean>> resultHistory(@PathVariable int page) {
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        List<ResultEntityBean> resultHistory = resultService.findResultByUserId(loginUser.get().getId(), page);
        return new SuccessBean<>(resultHistory);
    }

}

