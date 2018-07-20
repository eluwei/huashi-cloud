package com.huashi.cloud.common.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: LanJian Date: 12-8-2 Time: 上午8:44 To change
 * this template use File | Settings | File Templates.
 */
public class HttpUtils {
    /**
     * 组装httpget请求url
     *
     * @param map
     * @return
     */
    public static String httpGetUrl(String url, Map<String, String> map) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        Set<String> keys = map.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            String value = map.get(name);
            BasicNameValuePair nameValuePair = new BasicNameValuePair(name, value);
            params.add(nameValuePair);
        }
        String queryString = URLEncodedUtils.format(params, HTTP.UTF_8);
        return url + "?" + queryString;
    }

    /**
     * http get 请求发送
     *
     * @param url
     * @param parameters
     * @return
     * @throws HttpException
     */
    public static String httpGet(String url, Map parameters) throws HttpException {
        HttpGet get = new HttpGet(httpGetUrl(url, parameters));
        HttpClient http = new DefaultHttpClient();
        String response = null;
        try {
            HttpResponse httpResponse = http.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, HTTP.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("httpGet  error!");
        }
        return response;
    }

    /**
     * http post 请求发送
     *
     * @param url
     * @param parameters
     * @return
     * @throws HttpException
     */
    public static String httpPost(String url, Map parameters) throws HttpException {
        String response = null;
        try {

            HttpPost post = new HttpPost(url);
            post.setEntity(httpPostParameters(parameters));
            HttpClient http = new DefaultHttpClient();
            http = wrapClient(http);
            HttpResponse httpResponse = http.execute(post);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            throw new HttpException("httpPost  error", e);

        }
        return response;
    }

    /**
     * http delete
     * @param url
     * @return
     * @throws HttpException
     */
    public static String httpDelete(String url) throws HttpException {
        String response = null;
        try {
            HttpDelete delete = new HttpDelete(url);
            HttpClient http = new DefaultHttpClient();
            http = wrapClient(http);
            HttpResponse httpResponse = http.execute(delete);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            throw new HttpException("httpDelete  error", e);

        }
        return response;
    }

    /**
     * httpPut
     * @param url
     * @param parameters
     * @return
     * @throws HttpException
     */
    public static String httpPut(String url, Map parameters) throws HttpException {
        String response = null;
        try {
            HttpPut put = new HttpPut(url);
            put.setEntity(httpPostParameters(parameters));
            HttpClient http = new DefaultHttpClient();
            http = wrapClient(http);
            HttpResponse httpResponse = http.execute(put);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            throw new HttpException("httpPut  error", e);

        }
        return response;
    }

    /**
     * httpPatch
     * @param url
     * @param parameters
     * @return
     * @throws HttpException
     */
    public static String httpPatch(String url, Map parameters) throws HttpException {
        String response = null;
        try {
            HttpPatch patch = new HttpPatch(url);
            patch.setEntity(httpPostParameters(parameters));
            HttpClient http = new DefaultHttpClient();
            http = wrapClient(http);
            HttpResponse httpResponse = http.execute(patch);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            throw new HttpException("httpPatch  error", e);

        }
        return response;
    }

    public static void main(String[] args) throws HttpException {
        // String url =
        // "https://oauth.taobao.com/token?grant_type=authorization_code&client_id=21518346&client_secret=7893df9fa1aeda09b1b780432ec6d394&code=qPkfuAEBloZc43FE5uXMmX0w123406&redirect_uri=http://naryou.cn&view=web&state=";
        // String body = httpPost(url, new HashMap<String, Object>());
        // System.out.println(body);
        // System.out.println(httpGet("https://api.jpush.cn/v3/push", new
        // HashMap<String, Object>()));
        String url = "https://oauth.taobao.com/token?grant_type=authorization_code&client_id=21518346&client_secret=7893df9fa1aeda09b1b780432ec6d394&code=qPkfuAEBloZc43FE5uXMmX0w123406&redirect_uri=http://naryou.cn&view=web&state=";
        Map testHashMap = new HashMap<String, Object>();
        testHashMap.put("from", "iOS");
        testHashMap.put("passwd", "123456");
        testHashMap.put("tel", "18780127741");
        testHashMap.put("token", "779452");
        String body = httpPost("http://auth.meyouone.net/client/register", new HashMap<String, Object>());
        System.out.println(body);

    }

    public static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 组装httpost请求参数
     *
     * @param map
     * @return
     * @throws HttpException
     */
    public static UrlEncodedFormEntity httpPostParameters(Map<String, String> map) throws HttpException {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        Set<String> keys = map.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            String value = map.get(name);
            BasicNameValuePair nameValuePair = new BasicNameValuePair(name, value);
            params.add(nameValuePair);
        }
        try {
            return new UrlEncodedFormEntity(params, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new HttpException("httpPostParameters  error", e);
        }
    }
}