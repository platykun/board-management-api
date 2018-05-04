package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ルームリポジトリ.
 */
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    RoomEntity findById(int id);
}
