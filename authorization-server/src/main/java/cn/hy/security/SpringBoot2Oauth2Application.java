package cn.hy.security;

import cn.hy.security.Service.impl.AuthServiceImpl;
import cn.hy.security.properties.OAuth2Properties;
import cn.hy.security.utils.JsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;


@RestController
@SpringBootApplication
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启注解
public class SpringBoot2Oauth2Application {

    @Autowired
    private OAuth2Properties oAuth2Properties;




    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Oauth2Application.class, args);
    }




}
