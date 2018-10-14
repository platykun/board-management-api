package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 記録リポジトリ.
 */
public interface ResultRepository extends JpaRepository<ResultEntity, Integer> {
    Optional<ResultEntity> findByUserIdAndRoomId(String userId, int roomId);

    Page<ResultEntity> findByUserId(Pageable limit, String userId);
}