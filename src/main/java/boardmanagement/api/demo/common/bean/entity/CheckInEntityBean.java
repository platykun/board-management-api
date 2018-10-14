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
        this.timestamp = entity.getTimestamp();
        this.checkedOut = entity.isCheckedOut();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public CheckInEntity toEntity() {
        return new CheckInEntity(this.getId(), this.getUserId(), this.getPlaceId(), this.getTimestamp(), this.isCheckedOut());
    }
}
