package boardmanagement.api.demo.usecase.bean.response;

import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.usecase.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.entity.ResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 記録情報レスポンス用Beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseBean {

    /**
     * チェックイン場所
     */
    private PlaceEntityBean checkIn;

    /**
     * 自分の過去の履歴情報.
     */
    private List<ResultEntity> myHistory;

    /**
     * チェックイン場所の履歴情報.
     */
    private List<ResultEntity> nearHistory;

    /**
     * すべての履歴情報.
     */
    private List<ResultEntity> allHistory;

    public StatusResponseBean(UserStatusResponseDto dto) {
        // TODO: entityで返却しているが、EntityBeanで返却したい.
        this.checkIn = dto.getCheckIn();
        this.myHistory = dto.getMyHistory();
        this.nearHistory = dto.getNearHistory();
        this.allHistory = dto.getAllHistory();
    }
}
