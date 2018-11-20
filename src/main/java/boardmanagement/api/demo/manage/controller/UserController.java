package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.*;
import boardmanagement.api.demo.manage.bean.request.ResultRequestBean;
import boardmanagement.api.demo.manage.bean.request.UserResultRequestBean;
import boardmanagement.api.demo.manage.bean.response.ResultResponseBean;
import boardmanagement.api.demo.manage.bean.response.StatusResponseBean;
import boardmanagement.api.demo.manage.bean.response.UserResultResponseBean;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.StatusService;
import boardmanagement.api.demo.manage.service.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * ユーザステータス情報取得サービスクラス.
     */
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
        UserEntityBean loginUser = userService.findLoginUserFronSession();

        //TODO: チェックイン中のものはチェックアウト

        CheckInEntityBean registerdBean = checkInService.checkIn(loginUser.getId(), placeId);

        return new SuccessBean<>(registerdBean);
    }

    /**
     * チェックアウトする.
     *
     * @return チェックイン済情報
     */
    @PutMapping(path="checkout")
    SuccessBean<Integer> checkout(){
        UserEntityBean loginUser = userService.findLoginUserFronSession();

        Integer updateCount = checkInService.checkout(loginUser.getId());

        return new SuccessBean<>(updateCount);
    }

    /**
     * 結果を記録する.
     *
     * @param resultRequestBean 結果登録情報
     * @return 登録結果
     */
    @PutMapping(path="result")
    SuccessBean<ResultResponseBean> result(@RequestBody ResultRequestBean resultRequestBean){

        ResultResponseBean bean = resultService.register(resultRequestBean.toResultDto());
        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を記録する.
     *
     * @param userResultRequestBean 結果登録情報
     * @param id ID
     * @return 登録結果
     */
    @PutMapping(path="result/{id:^[0-9]+$}/new")
    SuccessBean<UserResultResponseBean> userResultNew(@RequestBody UserResultRequestBean userResultRequestBean, @PathVariable int id){

        UserResultResponseBean bean = resultService.registUserResult(id, userResultRequestBean.toResultDto());

        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を記録する.
     *
     * @param userResultRequestBean 結果登録情報
     * @param id ID
     * @return 登録結果
     */
    @PutMapping(path="result/{id:^[0-9]+$}/{resultId:^[0-9]+$}")
    SuccessBean<UserResultResponseBean> userResultUpdate(@RequestBody UserResultRequestBean userResultRequestBean,
                                                         @PathVariable("id") int id, @PathVariable("resultId") int resultId){

        UserResultResponseBean bean = resultService.updateUserResult(id, resultId, userResultRequestBean.toResultDto());

        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を記録する.
     *
     * @param id ID
     * @return 登録結果
     */
    @DeleteMapping(path="result/{id:^[0-9]+$}")
    SuccessBean<Boolean> userResultDelete(@PathVariable int id){

        Boolean result = resultService.deleteUserResult(id);

        return new SuccessBean<>(result);
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
        UserEntityBean loginUser = userService.findLoginUserFronSession();
        List<CheckInEntityBean> checkInHistory = checkInService.findCheckInByUserId(loginUser.getId(), page);

        return new SuccessBean<>(checkInHistory);
    }

    /**
     * ログインユーザの結果履歴を取得する.
     * @param page ページング
     * @return 結果履歴
     */
    @GetMapping(path="history/myresult/{page:^[a-z0-9]+$}")
    public SuccessBean<List<ResultResponseBean>> resultHistory(@PathVariable int page) {
        UserEntityBean loginUser = userService.findLoginUserFronSession();

        List<ResultResponseBean> resultHistory = resultService.findResultsByUserId(loginUser.getId(), page);
        return new SuccessBean<>(resultHistory);
    }

    /**
     * 記録IDから記録情報を取得する.
     * @param id 記録id.
     * @return 記録情報
     */
    @GetMapping(path="history/result/{id:^[0-9]+$}")
    public SuccessBean<ResultResponseBean> getResultById(@PathVariable int id) {
        ResultResponseBean resultHistory = resultService.findResultsByResultId(id);

        return new SuccessBean<>(resultHistory);
    }
}

