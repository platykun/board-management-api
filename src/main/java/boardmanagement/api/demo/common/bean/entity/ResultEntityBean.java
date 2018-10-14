package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 結果テーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntityBean {
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

    public ResultEntityBean(ResultEntity entity) {
        this.userId = entity.getUserId();
        this.roomId = entity.getRoomId();
        this.boardGameId = entity.getBoardGameId();
        this.boardGameTitle = entity.getBoardGameTitle();
        this.placeId = entity.getPlaceId();
        this.placeName = entity.getPlaceName();
        this.rank = entity.getRank();
        this.score = entity.getScore();
        this.comment = entity.getComment();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public ResultEntity convertToEntity(ResultEntityBean bean) {
        return new ResultEntity(bean.getUserId(), bean.getRoomId(), bean.getBoardGameId(), bean.getBoardGameTitle(),
                bean.getPlaceId(), bean.getPlaceName(), bean.getRank(), bean.getScore(), bean.getComment(), bean.getCreate());
    }


}
