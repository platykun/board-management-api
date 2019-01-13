package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.UserResultEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ユーザごとの記録リポジトリ
 */
@Repository
public interface UserResultRepository extends JpaRepository<UserResultEntity, Integer> {
    List<UserResultEntity> findByResultId(int resultId);

    List<UserResultEntity> findByUserId(Pageable limit, String userId);

    List<UserResultEntity> findByResultIdAndUserId(int resultId, String userId);
}
