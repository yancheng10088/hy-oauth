package cn.hy.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2018/1/24 0024.
 */
@Data
@ConfigurationProperties(prefix = "auth.security.oauth2")
public class OAuth2Properties {

    private String jwtSigningKey ;

    private OAuth2ClientProperties[] clients = {};
}
