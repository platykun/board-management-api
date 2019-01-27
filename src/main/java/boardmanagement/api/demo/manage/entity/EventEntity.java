package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * イベントテーブル.
 */
@Entity
@Table(name = "EVENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    /**
     * イベントID.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * 場所ID.
     */
    private int placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * 開始日時.
     */
    private Date date_time_from;

    /**
     * 終了日時.
     */
    private Date date_time_to;

    /**
     * コメント.
     */
    private String comment;
}
