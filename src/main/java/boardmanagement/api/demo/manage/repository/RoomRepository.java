package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * ルームリポジトリ.
 */
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    RoomEntity findById(int id);

    @Query(value = "SELECT r FROM RoomEntity r WHERE r.roomName LIKE %:roomName% ORDER BY r.id DESC")
    Page<RoomEntity> findByRoomName(@Param("roomName") String roomName, Pageable pageable);
}
