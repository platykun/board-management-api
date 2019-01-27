package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * チェックインテーブル.
 */
@Entity
@Table(name = "CHECK_IN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckInEntity {
    /**
     * チェックインID.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
     * チェックイン先のユーザID.場所IDとどちらかの値が格納される
     */
    private String placeUserId;

    /**
     * タイムスタンプ.
     */
    private Date timestampValue;

    /**
     * チェックアウトフラグ.
     */
    private boolean checkedOut;
}
