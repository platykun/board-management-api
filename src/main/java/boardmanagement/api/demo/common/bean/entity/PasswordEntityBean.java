package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.PasswordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * パスワードテーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEntityBean {
    /**
     * ユーザID.
     */
    private String userId;

    /**
     * パスワード.
     */
    private String password;

    public PasswordEntityBean(PasswordEntity entity) {
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public PasswordEntity toEntity() {
        return new PasswordEntity(this.getUserId(), this.getPassword());
    }
}
