package boardmanagement.api.demo.manage.bean.response;

import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
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
    private String checkIn;

    /**
     * 自分の過去の履歴情報.
     */
    private List<ResultEntityBean> myHistory;

    /**
     * チェックイン場所の履歴情報.
     */
    private List<ResultEntityBean> nearHistory;

    /**
     * すべての履歴情報.
     */
    private List<ResultEntityBean> allHistory;

    public StatusResponseBean(UserStatusResponseDto dto) {
        this.checkIn = dto.getCheckIn();
        this.myHistory = dto.getMyHistory();
        this.nearHistory = dto.getNearHistory();
        this.allHistory = dto.getAllHistory();
    }
}
