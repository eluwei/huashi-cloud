package com.huashi.cloud.common.qiniu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 七牛云存储管理器
 * 上传图像和查看图像
 */
@Component
public class QiniuStorage {

    @Autowired
    private QiniuWrapper qiniuWrapper;


    /**
     * 上传单张图片；返回上传图片的key
     * @param buff
     */
    public  String uploadImage(byte[] buff, String domain, String useType){
        String key = QiniuKeyGenerator.generateKey(domain, useType);
        key = qiniuWrapper.upload(buff, key,false);
        return key;
    }

    /**
     * 上传单张图片；返回上传图片的key
     * @param buff
     */
    public  String uploadImage(byte[] buff, String key, boolean update){
        key = qiniuWrapper.upload(buff, key, update);
        return key;
    }

    /**
     * 获取图片链接
     * @param key
     * @return
     */
    public String getUrl(String key){
        return qiniuWrapper.getUrl(key);
    }

    /**
     * 获取有有效期的图片链接
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public String getUrl(String key,long expires){
        return qiniuWrapper.getUrl(key,expires);
    }


}
