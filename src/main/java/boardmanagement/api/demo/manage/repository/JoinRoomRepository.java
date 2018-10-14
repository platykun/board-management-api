package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 参加リポジトリ.
 */
public interface JoinRoomRepository extends JpaRepository<JoinRoomEntity, Integer> {
    Optional<JoinRoomEntity> findByUserIdAndRoomId(String userId, int roomId);

    List<JoinRoomEntity> findByRoomId(int roomId);

    Page<JoinRoomEntity> findByUserIdOrderByCreateDesc(Pageable page, String userId);
}
