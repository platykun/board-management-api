package boardmanagement.api.demo.common.bean;

import boardmanagement.api.demo.common.constants.ResponseType;
import lombok.Data;

/**
 * 失敗時のレスポンスBeanクラス.
 */
@Data
public class FailureBean<T extends ErrorBean> implements ResponseBean {
    private ResponseType type = ResponseType.FAILURE;

    private T error;
}
