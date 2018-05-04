package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザリポジトリ.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(int id);
}
