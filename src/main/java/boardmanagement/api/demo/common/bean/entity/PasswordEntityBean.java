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
    private int userId;

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
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public PasswordEntity convertToEntity(PasswordEntityBean bean) {
        return new PasswordEntity(bean.getUserId(), bean.getPassword());
    }
}
