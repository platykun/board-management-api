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
    AppUserService userService;

    /**
     * チェックインサービスクラス.
     */
    private final
    CheckInService checkInService;

    @Autowired
    public UserCheckInController(AppUserService userService, CheckInService checkInService) {
        this.userService = userService;
        this.checkInService = checkInService;
    }

    /**
     * チェックインする.チェックイン中のものがあればチェックアウトする.
     *
     * @param placeId 場所ID
     * @return チェックイン済情報
     */
    @PutMapping(path="place/{placeId}/check-in")
    SuccessBean<CheckInEntityBean> checkin(@PathVariable int placeId){
        UserEntityBean loginUser = userService.findLoginUserFronSession();

        CheckInEntityBean registeredBean = checkInService.checkIn(loginUser.getId(), placeId);

        return new SuccessBean<>(registeredBean);
    }

    /**
     * チェックイン(宅ボドゲの場合)する.チェックイン中のものがあればチェックアウトする.
     *
     * @param userId チェックイン先ユーザID
     * @return チェックイン済情報
     */
    @PutMapping(path="place/home/{userId}/check-in")
    SuccessBean<CheckInEntityBean> checkin(@PathVariable String userId){
        UserEntityBean loginUser = userService.findLoginUserFronSession();

        CheckInEntityBean registeredBean = checkInService.checkInAtUserHome(loginUser.getId(), userId);

        return new SuccessBean<>(registeredBean);
    }

    /**
     * チェックアウトする.
     *
     * @return チェックイン済情報
     */
    @PutMapping(path="check-ins/checkout")
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
    @GetMapping(path="check-ins/me")
    public SuccessBean<List<CheckInEntityBean>> checkInHistory(
                @RequestParam(name = "page", required = false) Integer page
    ) {
        UserEntityBean loginUser = userService.findLoginUserFronSession();
        List<CheckInEntityBean> checkInHistory = checkInService.findCheckInByUserId(loginUser.getId(), page);

        return new SuccessBean<>(checkInHistory);
    }
}

