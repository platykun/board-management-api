package boardmanagement.api.demo.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ルーム登録時レスポンス返却用クラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
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
     * 備考.
     */
    private String remark;
}
