package com.huashi.cloud.common.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QiniuWrapper {

    @Autowired
    QiniuConn qiniuConn;

    private static final Logger logger = LoggerFactory.getLogger(QiniuWrapper.class);

    UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()) ); //设置空间上传域名
    private Auth auth = null;


    private Auth getAuthInstance () {
        if(auth == null)
              auth = Auth.create(qiniuConn.getAccesskey(), qiniuConn.getSecretkey());
        return auth;
    }

    /**
     * 获取上传资源的token
     * @return
     */
    public String getUploadToken(){
        return getAuthInstance().uploadToken(qiniuConn.getBucket());
    }

    /**
     * 获取更新资源的token，只能用于更新参数key所代表的资源
     * @param key 存储空间中已存在的资源key
     * @return
     */
    public String getUploadToken(String key){
        return getAuthInstance().uploadToken(qiniuConn.getBucket(), key);
    }

    /**
     * 上传文件
     * @param data 二进制格式的文件内容
     * @param key 文件在七牛中的key
     * @param update 是否是更新
     * @return
     */
    public String upload(byte[] data, String key, boolean update){
        try {
            String token = update ? getAuthInstance().uploadToken(qiniuConn.getBucket(), key) : getAuthInstance().uploadToken(qiniuConn.getBucket());
            Response response = uploadManager.put(data, update ? key : getFullKey(data, key), token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
            logger.error("upload file to qiniu cloud storage failed",e);
        }
        return "";
    }

    private static String getFullKey(byte[] data, String key){
        FileType type= FileTypeHelper.getType(data);
        if(type!=null){
            return key + "." + type.name().toLowerCase();
        }else{
            return key;
        }
    }


    public String getUrl(String key){
        if(!StringUtils.isEmpty(key)){
            return getAuthInstance().privateDownloadUrl("http://" + qiniuConn.getCdns() + "/@" + key);
        }
        return "";
    }

    /**
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public String getUrl(String key, long expires){
        if(!StringUtils.isEmpty(key)){
            long time = System.currentTimeMillis() / 1000 + expires;
            return getAuthInstance().privateDownloadUrl("http://" + qiniuConn.getCdns() + "/@" + key, time);
        }
        return "";
    }
}
