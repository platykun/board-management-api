package boardmanagement.api.demo.manage.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザのアイコン情報を更新するための値を格納するクラス.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIconUpdateRequestBean {

    /**
     * アイコン名.
     */
    private String icon;
    /**
     * アイコン色.
     */
    private String iconColor;

}
