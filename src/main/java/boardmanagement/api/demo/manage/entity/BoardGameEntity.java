package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ボードゲームテーブル.
 */
@Entity
@Table(name = "boardGame")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameEntity {
    /**
     * ボードゲームID.
     */
    @Id
    private int id;

    /**
     * ボードゲームタイトル.
     */
    private String title;

    /**
     * プレイ人数.
     */
    private int player;

    /**
     * 概要.
     */
    private String overview;
}
