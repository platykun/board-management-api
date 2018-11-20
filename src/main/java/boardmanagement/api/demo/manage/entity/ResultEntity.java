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
    @GeneratedValue
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
    private int placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * 作成日.
     */
    private Date create;
}
