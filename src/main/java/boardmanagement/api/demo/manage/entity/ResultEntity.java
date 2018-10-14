package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 結果テーブル.
 */
@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ResultEntity.PK.class)
public class ResultEntity {
    /**
     * ユーザID
     */
    @Id
    private String userId;

    /**
     * ルームID
     */
    @Id
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

    /**
     * 主キー定義
     */
    @Embeddable
    @Data
    public static class PK implements Serializable{
        @Column
        private String userId;

        @Column
        private int roomId;
    }
}
