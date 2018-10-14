package boardmanagement.api.demo.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJoinRoomDto {
    /**
     * ユーザID.
     */
    private String userId;
    /**
     * ルームID.
     */
    private int roomId;
    /**
     * ルーム作成者かどうか.
     */
    private boolean owner;
}
