package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 結果テーブル.
 */
@Entity
@Table(name = "RESULT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
    /**
     * 結果ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * ボードゲームID.
     */
    private int boardGameId;

    /**
     * ボードゲームタイトル.
     */
    private String boardGameTitle;

    /**
     * 場所ID.
     */
    private Integer placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * イベントID.
     */
    private Integer eventId;

    /**
     * 作成日.
     */
    @Column(name = "CREATE_DATE")
    private Date create;
}
