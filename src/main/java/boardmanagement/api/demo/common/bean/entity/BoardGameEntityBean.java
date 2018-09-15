package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ボードゲームテーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameEntityBean {
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

    public BoardGameEntityBean(BoardGameEntity entity) {
        this.title = entity.getTitle();
        this.player = entity.getPlayer();
        this.overview = entity.getOverview();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public BoardGameEntity toEntity() {
        return new BoardGameEntity(this.getTitle(), this.getPlayer(), this.getOverview());
    }
}
