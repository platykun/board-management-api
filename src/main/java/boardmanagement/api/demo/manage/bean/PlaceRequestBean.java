package boardmanagement.api.demo.manage.bean;

import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 場所テーブル登録用Beanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequestBean {
    /**
     * 場所名.
     */
    private String name;

    /**
     * URL.
     */
    private String url;

    /**
     * EntityBeanに変換する.
     * @return entityBean
     */
    public PlaceEntityBean toEntityBean() {
        return new PlaceEntityBean(0, this.name, this.url);
    }

    /**
     * ID付きのEntityBeanに変換する.
     * @return ID付きentityBean
     */
    public PlaceEntityBean toEntityBeanWithId(int id) {
        return new PlaceEntityBean(id, this.name, this.url);
    }
}
