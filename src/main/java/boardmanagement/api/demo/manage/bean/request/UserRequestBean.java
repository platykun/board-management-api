package boardmanagement.api.demo.manage.bean.request;

import boardmanagement.api.demo.manage.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザを作成するために利用するリクエスト用Beanクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBean {

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

    /**
     * UserRegisterDtoへ変換する.
     * @return UserRegisterDto
     */
    public UserRegisterDto toUserRegisterDto() {
        return new UserRegisterDto(this.id, this.name, this.authority, this.password);
    }

}
