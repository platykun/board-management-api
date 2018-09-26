package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 記録リポジトリ.
 */
public interface ResultRepository extends JpaRepository<ResultEntity, Integer> {
    Optional<ResultEntity> findByUserIdAndRoomId(int userId, int roomId);
}