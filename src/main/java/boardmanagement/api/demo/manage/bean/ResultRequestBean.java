package boardmanagement.api.demo.manage.bean;

import boardmanagement.api.demo.manage.dto.ResultDto;
import lombok.Data;

/**
 * 結果登録用Beanクラス.
 */
@Data
public class ResultRequestBean {
    /**
     * ユーザID.
     */
    private int userId;

    /**
     * 順位.
     */
    private int rank;

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
        return new ResultDto(this.userId, this.rank, this.score, this.comment);
    }
}
