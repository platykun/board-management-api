package boardmanagement.api.demo.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class SecurityProperties {

    @Value("security.encrypt.securityEncryptKey")
    private String securityEncryptKey;

    /**
     * 暗号鍵を取得する.
     * @return 暗号鍵
     */
    public String getSecurityEncryptKey() {
        return securityEncryptKey;
    }
}