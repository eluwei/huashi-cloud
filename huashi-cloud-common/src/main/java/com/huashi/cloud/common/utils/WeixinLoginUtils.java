package com.huashi.cloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信登录用户工具
 */
public class WeixinLoginUtils {

    private static Log log = LogFactory.getLog(WeixinLoginUtils.class);

    static String URL = "https://api.weixin.qq.com/sns/jscode2session";
    static String APPID = "wx7ea252b19c9cf7f0";
    static String SECRET = "2f12abd09357601f1e6cb7df6a07a793";
    static String GRANT_TYPE = "authorization_code";

    public static JSONObject getWeixinUserInfo(String code, String userInfoStr) {
        try {
            Map hashMap = new HashMap<String, Object>();
            hashMap.put("grant_type", GRANT_TYPE);
            hashMap.put("appid", APPID);
            hashMap.put("secret", SECRET);
            hashMap.put("js_code", code);
            String body = HttpUtils.httpGet(URL, hashMap);
            JSONObject userInfo = JSONObject.parseObject(userInfoStr);
            JSONObject sessionData = JSONObject.parseObject(body);
            if(userInfo.getString("signature").equals(checkUserComplete(userInfo.getString("rawData"), sessionData.getString("session_key")))){
                JSONObject weixinUserInfo = getUserInfo(userInfo.getString("encryptedData"), sessionData.getString("session_key"), userInfo.getString("iv"));
                return weixinUserInfo;
            }
        } catch (HttpException e) {
            e.printStackTrace();
            return null;
        }
       return null;
    }

    /**
     *  验证用户信息完整性
     * @author zhy
     * @param userInfoRawData 数据进行加密签名的密钥
     * @param sessionKey 数据进行加密签名的密钥
     * @return
     */
    public static String checkUserComplete(String userInfoRawData,String sessionKey) {
        return SHA1Utils.encode(userInfoRawData + sessionKey);
    }

    /**
     * 解密用户敏感数据获取用户信息
     *
     * @author zhy
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
                Security.addProvider(new BouncyCastleProvider());
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidParameterSpecException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
