package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.PlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 場所テーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceEntityBean {
    /**
     * 場所名.
     */
    private String name;

    /**
     * URL.
     */
    private String url;

    public PlaceEntityBean(PlaceEntity entity){
        this.name = entity.getName();
        this.url = entity.getUrl();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public PlaceEntity toEntity() {
        return new PlaceEntity(this.getName(), this.getUrl());
    }
}
