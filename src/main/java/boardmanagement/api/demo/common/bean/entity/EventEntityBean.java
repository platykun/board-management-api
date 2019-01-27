package boardmanagement.api.demo.common.bean.entity;

import boardmanagement.api.demo.manage.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * イベントテーブルBeanクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventEntityBean {
    /**
     * イベントID.
     */
    private int id;

    /**
     * 場所ID.
     */
    private int placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * 開始日時.
     */
    private String date_time_from;

    /**
     * 終了日時.
     */
    private String date_time_to;

    /**
     * コメント.
     */
    private String comment;

    /**
     * コンストラクタ
     * @param entity イベントエンティティ
     */
    public EventEntityBean(EventEntity entity){
        // simpleDateFormatはスレッドセーフでは無いため毎回newして利用する.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        this.id = entity.getId();
        this.placeId = entity.getPlaceId();
        this.placeName = entity.getPlaceName();
        this.date_time_from = simpleDateFormat.format(entity.getDate_time_from());
        this.date_time_to = simpleDateFormat.format(entity.getDate_time_to());
        this.comment = entity.getComment();
    }

    /**
     * BeanクラスからEntityクラスへ変換する.
     *
     * @return Entityクラス
     */
    public EventEntity toEntity() {
        // simpleDateFormatはスレッドセーフでは無いため毎回newして利用する.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return new EventEntity(
                    this.id,
                    this.placeId,
                    this.placeName,
                    simpleDateFormat.parse(this.date_time_from),
                    simpleDateFormat.parse(this.date_time_to),
                    this.comment
                    );
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
