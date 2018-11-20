package boardmanagement.api.demo.manage.bean.request;

import boardmanagement.api.demo.manage.dto.UserResultRegistDto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザごとの結果登録用Beanクラス.
 */
@Data
@AllArgsConstructor
public class UserResultRequestBean {

    private String userId;

    /**
     * スコア.
     */
    private String score;

    /**
     * コメント.
     */
    private String comment;

    public UserResultRegistDto toResultDto() {
        return new UserResultRegistDto(
                this.userId,
                this.score,
                this.comment
        );
    }
}
