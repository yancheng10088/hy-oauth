package cn.hy.security.controller;

import cn.hy.security.Service.impl.AuthServiceImpl;
import cn.hy.security.common.AjaxResult;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 安全controller
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServiceImpl authService;

    @Value("${accessToken.validity.seconds}")
    private int accessTokenValiditySeconds;

    @GetMapping("/genToken")
    public AjaxResult genToken(String clientId, String clientSecret){
        String token = authService.getAccessToken(clientId,clientSecret);
        JSONObject returnObject = new JSONObject();
        returnObject.put("token","Bearer"+token);
        returnObject.put("expires_in",accessTokenValiditySeconds);
        return AjaxResult.success(returnObject);
    }
}
