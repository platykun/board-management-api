package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ユーザリポジトリ.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findById(int id);

    Optional<UserEntity> findByName(String name);

    int countByName(String name);
}
