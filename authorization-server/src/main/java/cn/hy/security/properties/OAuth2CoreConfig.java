package cn.hy.security.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(OAuth2Properties.class)
public class OAuth2CoreConfig {
}
