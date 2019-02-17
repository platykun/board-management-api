package boardmanagement.api.demo.usecase.service;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.usecase.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.entity.ResultEntity;
import boardmanagement.api.demo.manage.service.base.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ユーザステータス情報取得サービスクラス.
 */
@Service
@RequiredArgsConstructor
public class StatusService {

    @NonNull
    private CheckInService checkInService;

    @NonNull
    private AppUserService userService;

    @NonNull
    private PlaceService placeService;

    @NonNull
    private ResultService resultService;


    /**
     * ログインユーザのステータス情報を取得する.
     *
     * @return ログインユーザのステータス情報
     */
    public UserStatusResponseDto getStatus() {

        // ユーザが見つからなかった場合Optionalでexceptionが送出される
        UserEntityBean loginUserBean = userService.findLoginUserFronSession();

        // ログイン者のチェックイン情報.取得できなかった場合、-1を返却する.
        Optional<CheckInEntityBean> checkInEntityBean = checkInService.findLatestCheckin(loginUserBean.getId());
        // 場所IDから場所名を割り出す。存在しない場合空文字.
        int placeId = checkInEntityBean.orElse(new CheckInEntityBean()).getPlaceId();

        // ステータス情報を取得する.
        PlaceEntityBean checkInPlace = checkInEntityBean.map(c -> placeService.findById(c.getPlaceId())).orElse(new PlaceEntityBean());

        List<ResultEntity> myHistory = resultService.findResultOutlineByUserId(loginUserBean.getId());
        List<ResultEntity> nearHistory = resultService.findResultOutlineByPlaceId(placeId);
        List<ResultEntity> allHistory = resultService.findResults(0);

        return new UserStatusResponseDto(checkInPlace, myHistory, nearHistory, allHistory);
    }
}
