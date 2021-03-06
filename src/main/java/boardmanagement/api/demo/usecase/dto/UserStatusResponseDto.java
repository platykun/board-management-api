package boardmanagement.api.demo.usecase.dto;

import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.entity.ResultEntity;
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

}
