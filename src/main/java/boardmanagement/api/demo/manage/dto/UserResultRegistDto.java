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
public class UserResultRegistDto {
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
}
