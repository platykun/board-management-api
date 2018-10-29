package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.bean.request.UserRequestBean;
import boardmanagement.api.demo.manage.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static boardmanagement.api.demo.security.config.SecurityConstants.SIGNUP_URL;

/**
 * ユーザ情報管理用コントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserControllController {
    // TODO: パスワード更新とかやる


    /**
     * ユーザサービスクラス.
     */
    private final
    UserService userService;

    @Autowired
    public UserControllController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 新しいユーザを作成する.
     * @return 作成済ユーザ
     */
    @PostMapping(SIGNUP_URL)
    SuccessBean<UserEntityBean> signup(@RequestBody UserRequestBean userRequestBean) {
        UserEntityBean result = userService.register(userRequestBean.toUserRegisterDto());
        return new SuccessBean<>(result);
    }
}
