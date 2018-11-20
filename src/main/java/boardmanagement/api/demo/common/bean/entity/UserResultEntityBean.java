package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.UserResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ユーザ結果テーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResultEntityBean {
    /**
     * ID
     */
    private int id;

    /**
     * 結果ID.
     */
    private int resultId;

    /**
     * ユーザID
     */
    private String userId;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;

    /**
     * 作成日.
     */
    private Date create;

    public UserResultEntityBean(UserResultEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.resultId = entity.getResultId();
        this.score = entity.getScore();
        this.comment = entity.getComment();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public UserResultEntity toEntity() {
        return new UserResultEntity(
                this.id,
                this.resultId,
                this.userId,
                this.score,
                this.comment
        );
    }
}
