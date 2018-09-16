package boardmanagement.api.demo.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザ情報取得用Dtoクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusDto {
    /**
     * ユーザID.
     */
    private int userId;

    /**
     * チェックイン中の場所.
     */
    private String checkInPlace;
}
