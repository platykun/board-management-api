package boardmanagement.api.demo.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 記録情報レスポンス用Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusResponseDto {
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
}
