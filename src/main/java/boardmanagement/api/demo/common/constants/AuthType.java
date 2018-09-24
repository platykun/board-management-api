package boardmanagement.api.demo.common.constants;

public enum AuthType {
    ADMIN("ADMIN"),
    USER("USER");

    private final String type;

    AuthType(String type) {
        this.type = type;
    }
}
