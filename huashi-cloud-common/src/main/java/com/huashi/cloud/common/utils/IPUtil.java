package com.huashi.cloud.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * IP工具<br/>
 *  存在伪造IP的漏洞,X-Forwarded-For该字段名称需可以自定义
 * @author yangfan 2013-10-15 下午2:37:14
 */
public class IPUtil {

    private static final String HAS_GET_REMOTE_ADDR_BY_IPUtil = "HAS_GET_REMOTE_ADDR_BY_IPUtil";

    private static final Logger log = LoggerFactory.getLogger(IPUtil.class);

    /**
     * 获取IP地址
     * <br/>主要解决经过nginx等代理后，无法获取IP的问题
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
//    //首先进行判断,避免死循环
//    if(request.getAttribute(HAS_GET_REMOTE_ADDR_BY_IPUtil) != null) {
//       return request.getRemoteAddr();
//    }

        String[] headers = new String[] {"X-Forwarded-For","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_X_FORWARDED_FOR","X-Real-IP"};
        String ip = tryGetIP(request,headers);
        if(ip == null) {
            ip = request.getRemoteAddr();
            log.debug("no ip found in request headers, then using default request.getRemoteAddr() method, ip=" + ip);
        }
        request.setAttribute(HAS_GET_REMOTE_ADDR_BY_IPUtil, true);

        return ip;
    }

    private static String tryGetIP(HttpServletRequest request, String[] headers) {
        for (String header : headers) {
            String value = request.getHeader(header);
            if(value != null && value.trim().length() > 0) {
                String ip = value.split("\\,")[0];
                log.debug("found ip using header: " + header + ", ip=" + ip);
                return ip;
            }
        }
        return null;
    }

    /**
     * 获取登录用户的IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 通过IP获取地址(需要联网，调用淘宝的IP库)
     *
     * @param ip
     * @return
     */
    public static String getIpInfo(String ip) {
        if (ip.equals("本地")) {
            ip = "127.0.0.1";
        }
        String info = "";
        try {
            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
            htpcon.setRequestMethod("GET");
            htpcon.setDoOutput(true);
            htpcon.setDoInput(true);
            htpcon.setUseCaches(false);

            InputStream in = htpcon.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            JSONObject obj = (JSONObject) JSON.parse(temp.toString());
            if (obj.getIntValue("code") == 0) {
                JSONObject data = obj.getJSONObject("data");
                info += data.getString("country") + " ";
                info += data.getString("region") + " ";
                info += data.getString("city") + " ";
                info += data.getString("isp");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
}
