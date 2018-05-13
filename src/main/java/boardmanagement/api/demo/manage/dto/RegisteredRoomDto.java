package boardmanagement.api.demo.manage.dto;

import lombok.Data;

/**
 * 登録済のルームを返却するためのDTO.
 */
@Data
public class RegisteredRoomDto {
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
