package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ボードゲームリポジトリ.
 */
@Repository
public interface BoardGameRepository extends JpaRepository<BoardGameEntity, String> {
    Optional<BoardGameEntity> findByTitle(String title);

    void deleteById(int boardGameId);

    @Query(value = "SELECT b FROM BoardGameEntity b WHERE b.title LIKE %:title% ORDER BY b.title DESC")
    Page<BoardGameEntity> findByName(@Param("title") String title, Pageable pageable);
}
