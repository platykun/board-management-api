package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.manage.bean.response.StatusResponseBean;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザステータスコントローラー
 */
@RestController
@CrossOrigin
public class UserStatusController {
    /**
     * ユーザステータス情報取得サービスクラス.
     */
    private final
    StatusService statusService;

    @Autowired
    public UserStatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * ログインユーザのステータス情報を取得する.
     * @return ログインユーザのステータス情報
     */
    @GetMapping(path="/user/status")
    SuccessBean<StatusResponseBean> status(){
        UserStatusResponseDto dto = statusService.getStatus();
        return new SuccessBean<>(new StatusResponseBean(dto));
    }
}

