package cn.hy.security.controller.auth;

import cn.hy.security.Service.impl.AuthServiceImpl;
import cn.hy.security.aspect.SysLog;
import cn.hy.security.common.AjaxResult;

import cn.hy.security.resource.util.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthController {
    @Autowired
    AuthServiceImpl authService;

    @Value("${accessToken.validity.seconds}")
    private int accessTokenValiditySeconds;

    @Value("${redirect.gongjiangtu.url}")
    private String gjtUrl ;

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;

    @SysLog()
    @GetMapping("/genToken")
    public AjaxResult genToken(String corpId, String corpSecret){
        JSONObject authJson = getCorpInfo(corpId,corpSecret);
        JSONObject authData = authJson.getJSONObject("data");
        JSONObject returnObject = new JSONObject();
        if(authData.getIntValue("validStatus")==1){
            String token = authService.getAccessToken(clientId,clientSecret);
            returnObject.put("token","Bearer"+token);
            returnObject.put("expires_in",accessTokenValiditySeconds);
        }else{
            returnObject.put("error",authData.getString("msg"));
        }
        return AjaxResult.success(returnObject);
    }

    public JSONObject getCorpInfo(String corpId,String corpSecret){
        String url = gjtUrl+"/auth/findAuthClient/"+corpId+"/"+corpSecret;
        String result = HttpUtil.getRequest(url,null);
        JSONObject resultObject = JSONObject.parseObject(result);
        return resultObject;
    }
}
