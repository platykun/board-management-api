package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 記録リポジトリ.
 */
public interface ResultRepository extends JpaRepository<ResultEntity, Integer> {
    Page<ResultEntity> findByUserId(Pageable limit, String userId);

    Page<ResultEntity> findByUserIdOrderByCreateDesc(Pageable limit, String userId);

    Page<ResultEntity> findByPlaceIdOrderByCreateDesc(Pageable limit, int placeId);

    // TODO: 日付順に並べ替える処理
    Page<ResultEntity> findAll(Pageable limit);
}