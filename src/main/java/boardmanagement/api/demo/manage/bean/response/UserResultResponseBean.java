package boardmanagement.api.demo.manage.bean.response;

import boardmanagement.api.demo.manage.dto.UserResultRegistDto;
import boardmanagement.api.demo.manage.entity.UserResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザごとの結果登録用Beanクラス.
 */
@Data
@AllArgsConstructor
public class UserResultResponseBean {

    /**
     * 結果ID
     */
    private int id;

    /**
     * 結果ID.
     */
    private int resultId;

    /**
     * ユーザID.
     */
    private String userId;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;

    public UserResultResponseBean(UserResultEntity u) {
        this.id = u.getId();
        this.resultId = u.getResultId();
        this.userId = u.getUserId();
        this.score = u.getScore();
        this.comment = u.getComment();

    }

    public UserResultRegistDto toResultDto() {
        return new UserResultRegistDto(
                this.userId,
                this.score,
                this.comment
        );
    }
}
