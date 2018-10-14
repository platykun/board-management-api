package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * パスワードテーブル.
 */
@Entity
@Table(name = "password")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordEntity {
    /**
     * ユーザID.
     */
    @Id
    private String userId;

    /**
     * パスワード.
     */
    private String password;
}
