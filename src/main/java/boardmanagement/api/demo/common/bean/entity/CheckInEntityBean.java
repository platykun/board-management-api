package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.CheckInEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * チェックインテーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInEntityBean {
    /**
     * チェックインID.
     */
    private int id;

    /**
     * ユーザID.
     */
    private String userId;

    /**
     * 場所ID.
     */
    private int placeId;

    /**
     * チェックイン先のユーザID.
     */
    private String placeUserId;

    /**
     * タイムスタンプ.
     */
    private Date timestamp;

    /**
     * チェックアウトフラグ.
     */
    private boolean checkedOut;

    public CheckInEntityBean(CheckInEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.placeId = entity.getPlaceId();
        this.placeUserId = entity.getPlaceUserId();
        this.timestamp = entity.getTimestampValue();
        this.checkedOut = entity.isCheckedOut();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public CheckInEntity toEntity() {
        return new CheckInEntity(
                this.id,
                this.userId,
                this.placeId,
                this.placeUserId,
                this.timestamp,
                this.checkedOut
        );
    }
}
