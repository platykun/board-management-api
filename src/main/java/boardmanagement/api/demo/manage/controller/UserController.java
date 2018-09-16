package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.manage.bean.CreateRoomRequestBean;
import boardmanagement.api.demo.common.bean.entity.JoinRoomEntityBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.bean.ResultRequestBean;
import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.service.base.CheckInService;
import boardmanagement.api.demo.manage.service.base.JoinRoomService;
import boardmanagement.api.demo.manage.service.base.ResultService;
import boardmanagement.api.demo.manage.service.base.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * ユーザコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

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

    /**
     * ルーム参加サービスクラス.
     */
    private final JoinRoomService joinRoomService;

    @Autowired
    public UserController(RoomService roomService, JoinRoomService joinRoomService, CheckInService checkInService, ResultService resultService) {
        this.roomService = roomService;
        this.joinRoomService = joinRoomService;
        this.checkInService = checkInService;
        this.resultService = resultService;
    }

    /**
     * ルームを作成する.
     * @param createRoomRequestBean ルーム情報
     * @return 作成結果
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/create_room")
    SuccessBean<RoomEntityBean> createRoom(@RequestBody CreateRoomRequestBean createRoomRequestBean, @PathVariable String id){

        RegisterRoomDto registerRoomDto = new RegisterRoomDto();
        BeanUtils.copyProperties(createRoomRequestBean, registerRoomDto);
        RoomEntityBean createdRoom = roomService.register(registerRoomDto);

        return new SuccessBean<>(createdRoom);
    }

    /**
     * ルームに参加する.
     * @param id ユーザID
     * @param roomId ルームID
     * @return 参加後のルーム情報
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/join/{roomId:^[a-z0-9]+$}")
    SuccessBean<JoinRoomEntityBean> joinRoom(@PathVariable String id, @PathVariable String roomId, Principal principal) {
        // idのユーザをroomIdのルームへ登録させる.
        int userIdNum = Integer.parseInt(id);
        int roomIdNum = Integer.parseInt(roomId);
        RegisterJoinRoomDto registerJoinRoomDto = new RegisterJoinRoomDto(userIdNum, roomIdNum, false);

        JoinRoomEntityBean result = joinRoomService.register(registerJoinRoomDto);

        return new SuccessBean<>(result);
    }

    /**
     * チェックインする.
     *
     * @param id ユーザID
     * @param placeId 場所ID
     * @return チェックイン済情報
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/checkin/{placeId:^[a-z0-9]+$}")
    SuccessBean<CheckInEntityBean> checkin(@PathVariable int id, @PathVariable int placeId, Principal principal){
        CheckInEntityBean registerdBean = checkInService.register(id, placeId);

        return new SuccessBean<>(registerdBean);
    }

    /**
     * 結果を記録する.
     *
     * @param id ユーザID
     * @param resultRequestBean 結果登録情報
     * @return 登録結果
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/result")
    SuccessBean<ResultEntityBean> result(@PathVariable int id, @RequestBody ResultRequestBean resultRequestBean){

        ResultEntityBean bean = resultService.register(resultRequestBean.toResultDto());

        return new SuccessBean<>(bean);
    }

}

