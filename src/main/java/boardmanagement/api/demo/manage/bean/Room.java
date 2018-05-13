package boardmanagement.api.demo.manage.bean;

import lombok.Data;

/**
 * ルーム登録時レスポンス返却用クラス.
 */
@Data
public class Room {
    /**
     * ルームID
     */
    private int id;

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
