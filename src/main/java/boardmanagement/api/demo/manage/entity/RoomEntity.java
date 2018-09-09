package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 記録テーブル.
 */
@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    /**
     * ルームID.
     */
    @Id
    @GeneratedValue
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
