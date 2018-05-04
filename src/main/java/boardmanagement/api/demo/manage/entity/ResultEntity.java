package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    private int userId;

    /**
     * ルームID
     */
    @Id
    private int roomId;

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
     * 主キー定義
     */
    @Embeddable
    @Data
    public static class PK implements Serializable{
        @Column
        private int userId;

        @Column
        private int roomId;
    }
}
