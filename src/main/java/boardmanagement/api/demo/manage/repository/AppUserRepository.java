package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.AppUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ユーザリポジトリ.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Integer> {
    Optional<AppUserEntity> findById(String id);

    Optional<AppUserEntity> findByName(String name);

    int countByName(String name);

    int countById(String id);

    @Query(value = "SELECT u FROM AppUserEntity u WHERE u.id LIKE %:id% ORDER BY u.id DESC")
    Page<AppUserEntity> findLikeId(@Param("id")String keyword, Pageable limit);
}
