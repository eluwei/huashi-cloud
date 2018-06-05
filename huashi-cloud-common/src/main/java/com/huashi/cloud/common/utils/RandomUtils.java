package com.huashi.cloud.common.utils;


import java.util.UUID;

/**
 * Created by Administrator on 2017/5/17.
 */
public class RandomUtils {

    private static int[] randoms = {0,1,2,3,4,5,6,7,8,9};

    /**
     * 生产指定位数的随机数字
     * @param length
     * @return
     */
    public static String randomCode(int length) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < length;i++) {
            int r = (int) (Math.random() * 10);
            builder.append(randoms[r]);
        }
        return builder.toString();
    }

    /**
     * 生产UUID
     * @return
     */
    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}