package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.CheckInEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * チェックインリポジトリ.
 */
@Repository
public interface CheckInRepository extends JpaRepository<CheckInEntity, Integer> {
    Optional<CheckInEntity> findById(int id);

    List<CheckInEntity> findByUserIdOrderByIdDesc(Pageable var1, int userId);

    Page<CheckInEntity> findByUserIdOrderByTimestampDesc(Pageable var1, int userId);

    /**
     * ユーザIDに紐づくすべての未チェックアウトデータをチェックアウト済にする.
     * TODO: 更新処理だが、トランザクション境界についての考慮が全くできていないため検討が必要。
     *
     * @param userId ユーザID
     * @return 更新件数
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE CheckInEntity c SET c.checkedOut = true WHERE c.userId = :userId AND c.checkedOut = false")
    Integer checkOutByUserId(@Param("userId") Integer userId);
}
