package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 記録テーブル.
 */
@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordEntity {
    /**
     * ユーザID.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * ボードゲームタイトル.
     */
    private String boardTitle;

    /**
     * プレイ人数.
     */
    private int player;

    /**
     * 備考.
     */
    private String remark;
}
