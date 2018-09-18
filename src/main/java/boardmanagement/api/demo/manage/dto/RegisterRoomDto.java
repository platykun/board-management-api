package boardmanagement.api.demo.manage.dto;

import lombok.Data;

/**
 * ルームを登録するためのDTO.
 */
@Data
public class RegisterRoomDto {

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
