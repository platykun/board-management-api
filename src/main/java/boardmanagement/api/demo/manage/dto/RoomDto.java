package boardmanagement.api.demo.manage.dto;

import boardmanagement.api.demo.manage.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ルームを登録するためのDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    /**
     * ルームID.
     */
    private int id;

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
     * RoomEntityに変換する.
     * @param create 作成日
     * @return RoomEntity
     */
    public RoomEntity toRoomEntity(Date create) {
        return new RoomEntity(
                this.id,
                this.roomName,
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                this.player,
                this.remark,
                this.status,
                create
        );
    }
}
