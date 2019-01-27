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
     * イベントID.
     */
    private int eventId;

    /**
     * 作成日.
     */
    private Date create;

    public ResultEntityBean(ResultEntity entity) {
        this.id = entity.getId();
        this.boardGameId = entity.getBoardGameId();
        this.boardGameTitle = entity.getBoardGameTitle();
        this.placeId = entity.getPlaceId();
        this.placeName = entity.getPlaceName();
        this.eventId = entity.getEventId();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public ResultEntity toEntity() {
        return new ResultEntity(
                this.id,
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                this.eventId,
                this.create
        );
    }


}
