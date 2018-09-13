package boardmanagement.api.demo.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ルーム参加beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomBean {
    /**
     * ユーザID.
     */
    private int userId;

    /**
     * 部屋ID.
     */
    private int roomId;

    /**
     * ルーム作成者かどうか
     */
    private boolean owner;
}
