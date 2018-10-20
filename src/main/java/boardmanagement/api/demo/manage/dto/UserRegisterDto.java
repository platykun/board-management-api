package boardmanagement.api.demo.manage.dto;

import boardmanagement.api.demo.common.bean.entity.PasswordEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザを作成するために利用するリクエスト用Beanクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    /**
     * ユーザID.
     */
    String id;
    /**
     * 名前.
     */
    String name;

    /**
     * 権限リスト.
     */
    int authority;

    /**
     * パスワード.
     */
    String password;
}
