package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.EventEntity;
import boardmanagement.api.demo.manage.entity.JoinEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * イベント参加テーブルのリポジトリクラス.
 */
@Repository
public interface JoinEventRepository extends JpaRepository<JoinEventEntity, String> {
    JoinEventEntity findById(int eventId);

    List<JoinEventEntity> findAllByEventId(int eventId);
}
