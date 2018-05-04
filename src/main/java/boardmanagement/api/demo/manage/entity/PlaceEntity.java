package boardmanagement.api.demo.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 場所テーブル.
 */
@Entity
@Table(name = "place")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceEntity {
    /**
     * 場所名.
     */
    @Id
    private String name;

    /**
     * URL.
     */
    private String url;
}
