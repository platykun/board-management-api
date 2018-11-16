package boardmanagement.api.demo.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 結果登録用Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRegistDto {
    /**
     * 親ID.
     */
    private Integer parentId;

    /**
     * ユーザID.
     */
    private String userId;
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
    private int placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;
}
