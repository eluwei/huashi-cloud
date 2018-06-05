package com.huashi.cloud.common.utils;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class SiteVerifyUtils {

    public static boolean checkByLuosimao(String regStr) throws Exception {
        String url = "https://captcha.luosimao.com/api/site_verify";
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("api_key", "lk19e6d88cec037ead700f2624600df47f");
        parameters.put("response", regStr);
        if(!StringUtils.isBlank(regStr)) {
            JSONObject result = JSON.parseObject(HttpUtils.httpPost(url, parameters));
            if ("success".equals(result.get("res"))) {
                return true;
            } else {
                System.out.println(result);
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkSignBySecretKey(String param,String sign,HttpServletRequest request) {
        try {
            String serverSign = MD5Utils.encode("chutang"+param);
            if(serverSign.equals(sign)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        SiteVerifyUtils.checkByLuosimao("ddddddddddd");
    }
}