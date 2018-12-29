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
    /**
     * アイコン名.
     */
    private String icon;
    /**
     * アイコン色.
     */
    private String iconColor;

    public UserEntityBean(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.authority = entity.getAuthority();
        this.icon = entity.getIcon();
        this.iconColor = entity.getIconColor();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public UserEntity toEntity() {
        return new UserEntity(
                this.id,
                this.name,
                this.authority,
                this.icon,
                this.iconColor
        );
    }
}
