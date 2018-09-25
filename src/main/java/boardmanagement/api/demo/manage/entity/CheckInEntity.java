package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @GeneratedValue
    private int id;

    /**
     * ユーザID.
     */
    private int userId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * タイムスタンプ.
     */
    private Date timestamp;

    /**
     * チェックアウトフラグ.
     */
    private boolean checkedOut;
}
