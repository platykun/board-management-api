package boardmanagement.api.demo.common.constants;

public enum ErrorType {
    MULTIPLE_ERROR("MultipleError"),
    FORM_ERROR("FormError");

    private final String type;

    ErrorType(String type) {
        this.type = type;
    }
}
