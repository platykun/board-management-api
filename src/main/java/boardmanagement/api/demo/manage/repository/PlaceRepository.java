package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 場所テーブル.
 */
@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, String> {
    PlaceEntity findByName(String name);
}
