package boardmanagement.api.demo.manage.bean;

import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 記録情報レスポンス用Beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseBean {
    /** 場所名. */
    private String placeName;

    /** ルーム名. */
    private String roomName;

    /** ボードゲームタイトル */
    private String boardTitle;

    /** プレイ人数 */
    private int player;

    /** 備考 */
    private String remark;

    /** 参加ユーザ名 */
    private List<String> joinPlayerName;

    public StatusResponseBean(UserStatusResponseDto dto) {
        this.placeName = dto.getPlaceName();
        this.roomName = dto.getRoomName();
        this.boardTitle = dto.getBoardTitle();
        this.player = dto.getPlayer();
        this.remark = dto.getRemark();
        this.joinPlayerName = dto.getJoinPlayerName();
    }
}
