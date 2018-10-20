package boardmanagement.api.demo.manage.bean;

import boardmanagement.api.demo.manage.dto.RoomDto;
import lombok.Data;

/**
 * ルーム作成用Beanクラス.
 */
@Data
public class RoomRequestBean {
    /**
     * ルーム名.
     */
    private String roomName;

    /**
     * ボードゲームID.
     */
    private int boardGameId;

    /**
     * ボードゲームタイトル.
     */
    private String boardGameTitle;

    /**
     * 場所ID.
     */
    private int placeId;

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

    /**
     * ルームステータス.
     */
    private int status;

    /**
     * RegisterRoomDtoを作成する.
     * @return RoomDto
     */
    public RoomDto toRegisterRoomDto() {
        return new RoomDto(
                0,
                this.roomName,
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                this.player,
                this.remark,
                this.status
        );
    }

    /**
     * ID付きでRegisterRoomDtoを作成する.
     * @return RoomDto
     */
    public RoomDto toRegisterRoomDtoWithId(int id) {
        return new RoomDto(
                id,
                this.roomName,
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                this.player,
                this.remark,
                this.status
        );
    }
}
