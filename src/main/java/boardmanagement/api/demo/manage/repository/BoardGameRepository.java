package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ボードゲームリポジトリ.
 */
@Repository
public interface BoardGameRepository extends JpaRepository<BoardGameEntity, String> {
    BoardGameEntity findByTitle(String title);
}
