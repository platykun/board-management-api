package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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

    @Id
    private int userId;

    @Id
    private int roomId;

    /**
     * ルーム作成者かどうか
     */
    private boolean owner;

    /**
     * 主キー定義
     */
    @Embeddable
    @Data
    public static class PK implements Serializable{

        @Column
        private int userId;

        @Column
        private int roomId;
    }
}
