package com.huashi.cloud.common.qiniu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiniuConn {

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.cdns}")
    private String cdns;

    @Value("${qiniu.accesskey}")
    private String accesskey;

    @Value("${qiniu.secretkey}")
    private String secretkey;


    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getCdns() {
        return cdns;
    }

    public void setCdns(String cdns) {
        this.cdns = cdns;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
}
