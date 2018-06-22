package com.huashi.cloud.common.qiniu;

import com.huashi.cloud.common.utils.UUIDGenerator;

import java.text.MessageFormat;

/**
 * 资源在七牛云存储的key生成器
 * 注：资源是七牛云存储服务中的逻辑存储单元。对于每一个账号，
 * 该账号里存放的每个资源都有唯一的一对属主空间（Bucket）与键名（Key），作为识别标识
 */
public class QiniuKeyGenerator {
    public static final String KEY = "/{0}/{1}/{2}";// 多图片可以按照：/表名/字段名/业务值(refId)/时间戳 处理
    public static String generateKey(String domain, String useType){
        return MessageFormat.format(KEY, domain, useType, UUIDGenerator.uuid());
    }

}