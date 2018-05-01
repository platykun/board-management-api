package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 記録リポジトリ.
 */
public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {
    RecordEntity findById(int id);
}
