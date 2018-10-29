package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.*;
import boardmanagement.api.demo.manage.bean.request.ResultRequestBean;
import boardmanagement.api.demo.manage.bean.response.StatusResponseBean;
import boardmanagement.api.demo.manage.dto.ResultDto;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.StatusService;
import boardmanagement.api.demo.manage.service.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ユーザコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    /**
     * ユーザサービスクラス.
     */
    private final
    UserService userService;

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
    StatusService statusService;

    @Autowired
    public UserController(UserService userService, CheckInService checkInService, ResultService resultService,
                          StatusService statusService) {
        this.userService = userService;
        this.checkInService = checkInService;
        this.resultService = resultService;
        this.statusService = statusService;
    }

    /**
     * チェックインする.チェックイン中のものがあればチェックアウトする.
     *
     * @param placeId 場所ID
     * @return チェックイン済情報
     */
    @PutMapping(path="checkin/{placeId}")
    SuccessBean<CheckInEntityBean> checkin(@PathVariable int placeId){
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }
        //TODO: チェックイン中のものはチェックアウト

        CheckInEntityBean registerdBean = checkInService.checkIn(loginUser.get().getId(), placeId);

        return new SuccessBean<>(registerdBean);
    }

    /**
     * チェックアウトする.
     *
     * @return チェックイン済情報
     */
    @PutMapping(path="checkout")
    SuccessBean<Integer> checkout(){
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        Integer updateCount = checkInService.checkout(loginUser.get().getId());

        return new SuccessBean<>(updateCount);
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
     * 親の結果に紐づけて結果を記録する.
     *
     * @param resultRequestBean 結果登録情報
     * @return 登録結果
     */
    @PutMapping(path="result/parent/{resultId:^[0-9]+$}")
    SuccessBean<ResultEntityBean> resultWithParent(@RequestBody ResultRequestBean resultRequestBean, @PathVariable int resultId){
        Optional<UserEntityBean> loginUser = userService.getLoginUser();
        if(!loginUser.isPresent()){
            throw new IllegalArgumentException("no login user");
        }

        // TODO: ログインユーザ情報を取得してDtoに詰める作業はServiceの責務にする
        ResultDto resultDto = resultRequestBean.toResultDto();
        resultDto.setUserId(loginUser.get().getId());
        resultDto.setParentId(resultId);

        ResultEntityBean bean = resultService.register(resultDto);

        return new SuccessBean<>(bean);
    }

    /**
     * ログインユーザのステータス情報を取得する.
     * @return ログインユーザのステータス情報
     */
    @GetMapping(path="status")
    SuccessBean<StatusResponseBean> status(){
        UserStatusResponseDto dto = statusService.getStatus();
        return new SuccessBean<>(new StatusResponseBean(dto));
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

        List<ResultEntityBean> resultHistory = resultService.findResultsByUserId(loginUser.get().getId(), page);
        return new SuccessBean<>(resultHistory);
    }

}

