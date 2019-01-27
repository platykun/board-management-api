package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * イベントテーブルのリポジトリクラス.
 */
@Repository
public interface EventRepository extends JpaRepository<EventEntity, String> {
    EventEntity findById(int eventId);

    /**
     * 有効なイベントを検索する。
     * 指定日付がfrom,toの間であること
     * 引数の値と同じユーザが紐づいていること
     * @param userId ユーザID
     * @param now 現在時刻
     * @return 有効なイベント一覧
     */
    @Query(value = "SELECT e FROM EventEntity e INNER JOIN e.joinEvent j ON j.userId = :userId " +
            "WHERE e.date_time_from < :now AND e.date_time_to > :now")
    List<EventEntity> findAllValidEvent(@Param("userId") String userId, @Param("now") Date now);
}
