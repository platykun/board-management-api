package boardmanagement.api.demo.manage.bean.request;

import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ボードゲームテーブル操作で利用するリクエスト用Beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameRequestBean {
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

    /**
     * BoardGameEntityBeanへ変換する.
     * @return BoardGameEntityBean
     */
    public BoardGameEntityBean toEntityBean(){
        return new BoardGameEntityBean(0, this.title, this.player, this.overview);
    }

    /**
     * ID付きのBoardGameEntityBeanへ変換する.
     * @return BoardGameEntityBean
     */
    public BoardGameEntityBean toEntityBeanWithId(int id) {
        return new BoardGameEntityBean(id, this.title, this.player, this.overview);
    }
}
