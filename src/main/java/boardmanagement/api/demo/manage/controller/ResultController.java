package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.bean.request.ResultRequestBean;
import boardmanagement.api.demo.manage.bean.request.UserResultRequestBean;
import boardmanagement.api.demo.manage.bean.response.ResultResponseBean;
import boardmanagement.api.demo.manage.bean.response.UserResultResponseBean;
import boardmanagement.api.demo.manage.service.base.ResultService;
import boardmanagement.api.demo.manage.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 結果コントローラー
 */
@RestController
@CrossOrigin
public class ResultController {

    /**
     * ユーザサービスクラス.
     */
    private final
    UserService userService;

    /**
     * 結果情報サービスクラス.
     */
    private final
    ResultService resultService;

    @Autowired
    public ResultController(UserService userService, ResultService resultService) {
        this.userService = userService;
        this.resultService = resultService;
    }

    /**
     * 結果を記録する.
     *
     * @param resultRequestBean 結果登録情報
     * @return 登録結果
     */
    @PutMapping(path="/user/result")
    SuccessBean<ResultResponseBean> result(@RequestBody ResultRequestBean resultRequestBean){

        ResultResponseBean bean = resultService.register(resultRequestBean.toResultDto());
        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を記録する.
     *
     * @param userResultRequestBean 結果登録情報
     * @param resultId ID
     * @return 登録結果
     */
    @PutMapping(path="/user/user_result/{resultId:^[0-9]+$}/new")
    SuccessBean<UserResultResponseBean> userResultNew(@RequestBody UserResultRequestBean userResultRequestBean, @PathVariable int resultId){

        UserResultResponseBean bean = resultService.registUserResult(resultId, userResultRequestBean.toResultDto());

        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を更新する.
     *
     * @param userResultRequestBean 結果登録情報
     * @param id ID
     * @return 登録結果
     */
    @PutMapping(path="/user/user_result/{id:^[0-9]+$}/{resultId:^[0-9]+$}")
    SuccessBean<UserResultResponseBean> userResultUpdate(@RequestBody UserResultRequestBean userResultRequestBean,
                                                         @PathVariable("id") int id, @PathVariable("resultId") int resultId){

        UserResultResponseBean bean = resultService.updateUserResult(id, resultId, userResultRequestBean.toResultDto());

        return new SuccessBean<>(bean);
    }

    /**
     * ユーザごとの結果を削除する.
     *
     * @param id ID
     * @return 削除結果
     */
    @DeleteMapping(path="/user/user_result/{id:^[0-9]+$}")
    SuccessBean<Boolean> userResultDelete(@PathVariable int id){

        Boolean result = resultService.deleteUserResult(id);

        return new SuccessBean<>(result);
    }

    /**
     * ログインユーザの結果履歴を取得する.
     * @param page ページング
     * @return 結果履歴
     */
    @GetMapping(path="/user/history/myresult/{page:^[a-z0-9]+$}")
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
    @GetMapping(path="/user/history/result/{id:^[0-9]+$}")
    public SuccessBean<ResultResponseBean> getResultById(@PathVariable int id) {
        ResultResponseBean resultHistory = resultService.findResultsByResultId(id);

        return new SuccessBean<>(resultHistory);
    }
}

