package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.PasswordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * パスワードテーブル.
 */
@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Integer> {
    PasswordEntity findById(int id);
}
