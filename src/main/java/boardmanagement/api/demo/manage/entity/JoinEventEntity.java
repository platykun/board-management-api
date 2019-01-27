package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * イベント参加テーブル.
 */
@Entity
@Table(name = "JOIN_EVENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinEventEntity {
    /**
     * イベント参加テーブルID.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * イベントID.
     */
    private int eventId;

    /**
     * ユーザID.
     */
    private String userId;
}
