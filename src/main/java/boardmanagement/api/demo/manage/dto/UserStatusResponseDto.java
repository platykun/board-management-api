package boardmanagement.api.demo.manage.dto;

import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 記録情報レスポンス用Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusResponseDto {

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

}
