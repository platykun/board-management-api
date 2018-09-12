package boardmanagement.api.demo.common.bean;

import boardmanagement.api.demo.common.constants.ResponseType;
import lombok.Data;

/**
 * 成功時のレスポンスBeanクラス.
 */
@Data
public class SuccessBean<T> implements ResponseBean {
    private ResponseType type = ResponseType.SUCCESS;

    private T result;

    public SuccessBean(T result) {
        this.result = result;
    }
}
