package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 結果テーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntityBean {
    /**
     * ユーザID
     */
    private int userId;

    /**
     * ルームID
     */
    private int roomId;

    /**
     * 順位.
     */
    private int rank;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;

    public ResultEntityBean(ResultEntity entity) {
        this.userId = entity.getUserId();
        this.roomId = entity.getRoomId();
        this.rank = entity.getRank();
        this.score = entity.getScore();
        this.comment = entity.getComment();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public ResultEntity convertToEntity(ResultEntityBean bean) {
        return new ResultEntity(bean.getUserId(), bean.getRoomId(), bean.getRank(), bean.getScore(), bean.getComment());
    }


}
