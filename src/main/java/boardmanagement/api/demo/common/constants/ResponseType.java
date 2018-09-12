package boardmanagement.api.demo.common.constants;

public enum ResponseType {
    SUCCESS("success"),
    FAILURE("failure");

    private final String value;

    ResponseType(String value) {
        this.value = value;
    }
}
