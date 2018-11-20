package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 結果テーブル.
 */
@Entity
@Table(name = "USER_RESULT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResultEntity {
    /**
     * 結果ID
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * 結果ID.
     */
    private int resultId;

    /**
     * ユーザID
     */
    private String userId;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;
}
