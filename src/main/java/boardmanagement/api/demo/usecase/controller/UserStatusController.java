package boardmanagement.api.demo.usecase.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.usecase.bean.response.StatusResponseBean;
import boardmanagement.api.demo.usecase.dto.UserStatusResponseDto;
import boardmanagement.api.demo.usecase.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザステータスコントローラー
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserStatusController {
    /**
     * ユーザステータス情報取得サービスクラス.
     */
    private final
    StatusService statusService;

    /**
     * ログインユーザのステータス情報を取得する.
     * @return ログインユーザのステータス情報
     */
    @GetMapping(path="/users/me/status")
    SuccessBean<StatusResponseBean> status(){
        UserStatusResponseDto dto = statusService.getStatus();
        return new SuccessBean<>(new StatusResponseBean(dto));
    }
}
