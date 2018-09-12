package boardmanagement.api.demo.common.bean;


import boardmanagement.api.demo.common.constants.ResponseType;

/**
 * APIのレスポンス情報の最上位インターフェース.
 */
public interface ResponseBean {
    ResponseType type = ResponseType.SUCCESS;
}
