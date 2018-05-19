package boardmanagement.api.demo.security.config;

import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO: 適切な場所で定義する.
 * ユーザ情報を格納する.
 */
public class UserForm {
    private String loginId;

    private String pass;

    // TODO: lombokで付与されずやむを得ず実装
    public String getLoginId() {
        return loginId;
    }

    // TODO: lombokで付与されずやむを得ず実装
    public String getPass(){
        return pass;
    }

    public void encrypt(PasswordEncoder encoder){
        this.pass = encoder.encode(pass);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "loginId='" + loginId + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}