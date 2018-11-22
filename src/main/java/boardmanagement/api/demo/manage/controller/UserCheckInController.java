package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.*;
import boardmanagement.api.demo.manage.service.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * チェックインコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserCheckInController {

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

    @Autowired
    public UserCheckInController(UserService userService, CheckInService checkInService) {
        this.userService = userService;
        this.checkInService = checkInService;
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
}

