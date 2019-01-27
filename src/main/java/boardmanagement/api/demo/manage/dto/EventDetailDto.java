package boardmanagement.api.demo.manage.dto;

import boardmanagement.api.demo.common.bean.entity.EventEntityBean;
import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * イベント情報詳細Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailDto {
    /**
     * イベント情報.
     */
    private EventEntityBean eventDetail;

    /**
     * 参加者一覧.
     */
    private List<UserEntityBean> joinUserList;

    /**
     * 結果一覧.
     */
    private List<ResultEntityBean> resultList;
}
