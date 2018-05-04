package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 参加リポジトリ.
 */
public interface JoinRoomRepository extends JpaRepository<JoinRoomEntity, Integer> {
    JoinRoomEntity findByUserIdAndRoomId(int userId, int roomId);
}
