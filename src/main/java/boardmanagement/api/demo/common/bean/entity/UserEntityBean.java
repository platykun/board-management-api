package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザテーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityBean {
    /**
     * ユーザID.
     */
    private String id;

    /**
     * ユーザ名.
     */
    private String name;

    /**
     * 権限.
     */
    private int authority;

    public UserEntityBean(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.authority = entity.getAuthority();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @param bean Beanクラス
     * @return Entityクラス
     */
    public UserEntity convertToEntity(UserEntityBean bean) {
        return new UserEntity(bean.getId(), bean.getName(), bean.getAuthority());
    }
}
