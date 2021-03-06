package boardmanagement.api.demo.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 結果登録用Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRegistDto {
    /**
     * ボードゲームID.
     */
    private int boardGameId;

    /**
     * ボードゲームタイトル.
     */
    private String boardGameTitle;

    /**
     * 場所ID.
     */
    private Integer placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * イベントID.
     */
    private Integer eventId;

    /**
     * ユーザ結果リスト.
     */
    private List<UserResultRegistDto> userResultList;

}
