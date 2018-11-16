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
     * ID
     */
    private int id;

    /**
     * 親ID
     */
    private int parentId;

    /**
     * ユーザID
     */
    private String userId;

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
        this.id = entity.getId();
        this.parentId = entity.getParentId();
        this.userId = entity.getUserId();
        this.boardGameId = entity.getBoardGameId();
        this.boardGameTitle = entity.getBoardGameTitle();
        this.placeId = entity.getPlaceId();
        this.placeName = entity.getPlaceName();
        this.score = entity.getScore();
        this.comment = entity.getComment();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public ResultEntity toEntity() {
        return new ResultEntity(
                this.getId(),
                this.getParentId(),
                this.getUserId(),
                this.getBoardGameId(),
                this.getBoardGameTitle(),
                this.getPlaceId(),
                this.getPlaceName(),
                this.getScore(),
                this.getComment(),
                this.getCreate()
        );
    }


}
