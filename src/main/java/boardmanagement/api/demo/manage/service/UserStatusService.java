package boardmanagement.api.demo.manage.service;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.manage.dto.UserStatusDto;
import boardmanagement.api.demo.manage.service.base.CheckInService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * ユーザステータス情報取得サービスクラス.
 */
public class UserStatusService {

    @NonNull
    private CheckInService checkInService;

    @Autowired
    public UserStatusService(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    public UserStatusDto findUserStatus(int userId) {
        CheckInEntityBean checkInStatus = checkInService.findLatestCheckin(userId);
        UserStatusDto userStatusDto = new UserStatusDto();
        userStatusDto.setCheckInPlace("hoge");
        return userStatusDto;
    }
}
