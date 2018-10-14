package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ルーム参加beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomEntityBean {
    /**
     * ユーザID.
     */
    private String userId;

    /**
     * 部屋ID.
     */
    private int roomId;

    /**
     * ルーム作成者かどうか
     */
    private boolean owner;

    /**
     * 作成日
     */
    private Date create;

    public JoinRoomEntityBean(JoinRoomEntity entity) {
        this.userId = entity.getUserId();
        this.roomId = entity.getRoomId();
        this.owner = entity.isOwner();
        this.create = entity.getCreate();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public JoinRoomEntity convertToEntity(JoinRoomEntityBean bean) {
        return new JoinRoomEntity(bean.getUserId(), bean.getRoomId(), bean.isOwner(), bean.getCreate());
    }
}
