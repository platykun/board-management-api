package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ルーム登録時レスポンス返却用クラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntityBean {
    /**
     * ルームID.
     */
    private int id;

    /**
     * ルーム名.
     */
    private String roomName;

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
     * プレイ人数.
     */
    private int player;

    /**
     * 備考.
     */
    private String remark;

    /**
     * ルームステータス
     */
    private int status;

    /**
     * 作成日
     */
    private Date create;

    public RoomEntityBean(RoomEntity entity) {
        this.id = entity.getId();
        this.roomName = entity.getRoomName();
        this.boardGameId = entity.getBoardGameId();
        this.boardGameTitle = entity.getBoardGameTitle();
        this.placeId = entity.getPlaceId();
        this.placeName = entity.getPlaceName();
        this.player = entity.getPlayer();
        this.remark = entity.getRemark();
        this.status = entity.getStatus();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public RoomEntity convertToEntity(RoomEntityBean bean) {
        return new RoomEntity(bean.getId(), bean.getRoomName(), bean.getBoardGameId(), bean.getBoardGameTitle(),
                bean.getPlaceId(), bean.getPlaceName(), bean.getPlayer(), bean.getRemark(), bean.getStatus(),
                bean.getCreate());
    }
}
