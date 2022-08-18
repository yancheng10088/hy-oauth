package cn.hy.security.utils;

/**
 * @author cnh
 * @date 2022/8/17
 * @description TODO
 */
public class ClientUtil {
    private static String preKey = "HY_OAUTH%@#";
    public static String createClientId(){
        return IdUtils.Guid();
    }

    public static String createClientSecret(String clientId){
        return MD5Utils.MD5(preKey+clientId+System.currentTimeMillis());
    }


    public static void main(String[] args) {
        System.out.println(createClientId());
        System.out.println(createClientSecret(createClientId()));

    }
}
