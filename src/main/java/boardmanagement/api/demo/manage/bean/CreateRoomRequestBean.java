package boardmanagement.api.demo.manage.bean;

import lombok.Data;

/**
 * ルーム作成用Beanクラス.
 */
@Data
public class CreateRoomRequestBean {
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
