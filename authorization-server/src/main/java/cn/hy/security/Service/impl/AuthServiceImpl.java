package cn.hy.security.Service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthServiceImpl {
    @Value("${auth-server}")
    String authServer;

    /**
     * 获取accessToken
     * @return
     */
    public String getAccessToken(String clientId,String clientSecret) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("authorization", getBasicAuthHeader(clientId,clientSecret));
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String tokenRequestUrl = authServer + "/oauth/token?grant_type=password&username=admin&password=123456&scope=all";
        ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(tokenRequestUrl, entity, OAuth2AccessToken.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        OAuth2AccessToken t = resp.getBody();
        return t.getValue();
    }


    /**
     * 构建header
     * @return
     */
    private static String getBasicAuthHeader(String clientId,String clientSecret) {
        String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        log.info("authHeader:"+authHeader);
        return authHeader;
    }
}
