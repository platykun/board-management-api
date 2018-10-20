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
@Table(name = "result")
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
     * ユーザID
     */
    private String userId;

    /**
     * ルームID
     */
    private int roomId;

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

    /**
     * 作成日.
     */
    private Date create;
}
