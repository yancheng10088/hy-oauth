package cn.hy.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "auth.security.oauth2")
public class OAuth2Properties {

    private String jwtSigningKey ;

    private OAuth2ClientProperties[] clients = {};
}
