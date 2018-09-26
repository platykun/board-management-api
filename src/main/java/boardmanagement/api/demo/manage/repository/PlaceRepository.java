package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.PlaceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 場所テーブル.
 */
@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, String> {
    Optional<PlaceEntity> findByName(String name);

    @Query(value = "SELECT p FROM PlaceEntity p WHERE p.name LIKE %:name% ORDER BY p.name DESC")
    Page<PlaceEntity> findByName(@Param("name") String name, Pageable pageable);
}
