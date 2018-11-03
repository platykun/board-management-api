package boardmanagement.api.demo.manage.bean.request;

import boardmanagement.api.demo.manage.dto.ResultDto;
import lombok.Data;

/**
 * 結果登録用Beanクラス.
 */
@Data
public class ResultRequestBean {
    /**
     * 親ID.
     */
    private Integer parentId;

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

    /**
     * ResultDtoに変換する.
     * @return ResultDto
     */
    public ResultDto toResultDto() {
        return new ResultDto(
                this.parentId,
                null,
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                this.score,
                this.comment
        );
    }
}
