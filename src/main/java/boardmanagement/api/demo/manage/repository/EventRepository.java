package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * イベントテーブルのリポジトリクラス.
 */
@Repository
public interface EventRepository extends JpaRepository<EventEntity, String> {
    EventEntity findById(int eventId);
}
