package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 参加テーブル.
 */
@Entity
@Table(name = "join_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(JoinRoomEntity.PK.class)
public class JoinRoomEntity {

    /**
     * ユーザID.
     */
    @Id
    private String userId;

    /**
     * 部屋ID.
     */
    @Id
    private int roomId;

    /**
     * ルーム作成者かどうか
     */
    private boolean owner;

    /**
     * 参加時間
     */
    private Date create;

    /**
     * 主キー定義
     */
    @Embeddable
    @Data
    public static class PK implements Serializable{

        @Column
        private String userId;

        @Column
        private int roomId;
    }
}
