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
        return new ResultDto(this.parentId, null, this.score, this.comment);
    }
}
