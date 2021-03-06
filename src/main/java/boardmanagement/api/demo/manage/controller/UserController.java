package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.bean.request.UserIconUpdateRequestBean;
import boardmanagement.api.demo.manage.bean.request.UserRequestBean;
import boardmanagement.api.demo.manage.service.base.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static boardmanagement.api.demo.security.config.SecurityConstants.SIGNUP_URL;

/**
 * ユーザ情報管理用コントローラー
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    // TODO: パスワード更新とかやる


    /**
     * ユーザサービスクラス.
     */
    private final
    AppUserService userService;


    /**
     * 新しいユーザを作成する.
     * @return 作成済ユーザ
     */
    @PostMapping(SIGNUP_URL)
    SuccessBean<UserEntityBean> signup(@RequestBody UserRequestBean userRequestBean) {
        // TODO: ユーザ存在チェックを行う
        // TODO: 管理者ユーザはこのエンドポイントからは作成できいないようにする
        // TODO: 管理者ユーザは/admin/signupのような名前にする
        UserEntityBean result = userService.register(userRequestBean.toUserRegisterDto());
        return new SuccessBean<>(result);
    }

    /**
     * ユーザIDが利用できるかどうかチェックする.
     * @param userId ユーザID
     * @return 利用有無
     */
    @GetMapping("/users/{userId}/available")
    public SuccessBean<Boolean> existUser(@PathVariable String userId) {
        Boolean result = userService.isAvailableUser(userId);

        return new SuccessBean<>(result);
    }

    @GetMapping("/users")
    public SuccessBean<List<UserEntityBean>> find(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "q", required = false) String query
    ) {
        List<UserEntityBean> userEntityBeanList = userService.findLikeId(query);

        return new SuccessBean<>(userEntityBeanList);
    }

    /**
     * ユーザのアイコン情報を更新する.
     * @return 登録済み情報
     */
    @PutMapping("/users/me/icon")
    public SuccessBean<UserEntityBean> configurationIcon(@RequestBody UserIconUpdateRequestBean userIconUpdateRequestBean) {

        String icon = userIconUpdateRequestBean.getIcon();
        String iconClor = userIconUpdateRequestBean.getIconColor();
        UserEntityBean updatedUser = userService.updateUserIcon(icon, iconClor);

        return new SuccessBean<>(updatedUser);
    }

    /**
     * ユーザの詳細情報を取得する.
     * @param userId ユーザID
     * @return ユーザの詳細情報
     */
    @GetMapping("/users/{userId}")
    public SuccessBean<UserEntityBean> getUserDetail(@PathVariable String userId) {
        Optional<UserEntityBean> user = userService.findByUserId(userId);

        // ユーザが検索できなかった場合エラーを返却する
        // TODO: このときにErrorBeanで返却できるようにする
        if(!user.isPresent()){
            throw new IllegalArgumentException();
        }

        return new SuccessBean<>(user.get());
    }
}
