package boardmanagement.api.demo.manage.service;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.base.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ユーザステータス情報取得サービスクラス.
 */
@Service
public class StatusService {

    @NonNull
    private CheckInService checkInService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ResultService resultService;

    @Autowired
    public StatusService(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    /**
     * ログインユーザのステータス情報を取得する.
     *
     * @return ログインユーザのステータス情報
     */
    public UserStatusResponseDto getStatus() {

        // ユーザが見つからなかった場合Optionalでexceptionが送出される
        UserEntityBean loginUserBean = userService.getLoginUser().get();

        // ログイン者のチェックイン情報.取得できなかった場合、-1を返却する.
        Optional<CheckInEntityBean> checkInEntityBean = checkInService.findLatestCheckin(loginUserBean.getId());
        // 場所IDから場所名を割り出す。存在しない場合空文字.
        int placeId = checkInEntityBean.orElse(new CheckInEntityBean()).getPlaceId();

        // ステータス情報を取得する.
        String placeName = checkInEntityBean.map(c -> placeService.findById(c.getPlaceId()).getName()).orElse("");
        List<ResultEntityBean> myHistory = resultService.findResultsByUserId(loginUserBean.getId(), 0);
        List<ResultEntityBean> nearHistory = resultService.findResultsByPlaceId(placeId, 0);
        List<ResultEntityBean> allHistory = resultService.findResults(0);

        return new UserStatusResponseDto(placeName, myHistory, nearHistory, allHistory);
    }
}
