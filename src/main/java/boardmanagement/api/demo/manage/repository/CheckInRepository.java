package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.CheckInEntity;
import boardmanagement.api.demo.manage.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * チェックインリポジトリ.
 */
@Repository
public interface CheckInRepository extends JpaRepository<CheckInEntity, Integer> {
    CheckInEntity findById(int id);

    List<CheckInEntity> findByUserIdOrderByIdDesc(Pageable var1, int userId);
}
