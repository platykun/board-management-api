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
     * ボードゲームタイトル.
     */
    private String boardTitle;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * プレイ人数.
     */
    private int player;

    /**
     * 作成日
     */
    private Date create;

    /**
     * 備考.
     */
    private String remark;

    public RoomEntityBean(RoomEntity entity) {
        this.id = entity.getId();
        this.roomName = entity.getRoomName();
        this.boardTitle = entity.getBoardTitle();
        this.placeName = entity.getPlaceName();
        this.player = entity.getPlayer();
        this.remark = entity.getRemark();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public RoomEntity convertToEntity(RoomEntityBean bean) {
        return new RoomEntity(bean.getId(), bean.getRoomName(), bean.getBoardTitle(),
                bean.getPlaceName(), bean.getPlayer(), bean.getRemark(), bean.getCreate());
    }
}
