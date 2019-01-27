package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.JoinEventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * イベント参加テーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinEventEntityBean {
    /**
     * イベント参加テーブルID.
     */
    private int id;

    /**
     * イベントID.
     */
    private int eventId;

    /**
     * ユーザID.
     */
    private String userId;

    /**
     * コンストラクタ
     * @param entity イベントエンティティ
     */
    public JoinEventEntityBean(JoinEventEntity entity){
        this.id = entity.getId();
        this.eventId = entity.getEventId();
        this.userId = entity.getUserId();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public JoinEventEntity toEntity() {
        return new JoinEventEntity(
                this.id,
                this.eventId,
                this.userId
                );
    }
}
