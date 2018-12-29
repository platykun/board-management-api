package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ユーザテーブル.
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    /**
     * ユーザID.
     */
    @Id
    private String id;

    /**
     * ユーザ名.
     */
    private String name;

    /**
     * 権限.
     */
    private int authority;

    /**
     * アイコンテキスト
     */
    private String icon;

    /**
     * アイコン色(CSS)
     */
    private String iconColor;
}
