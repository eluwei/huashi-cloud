package com.huashi.cloud.customer.common.domain.request;


/**
 **********************************************
 *  描述：
 * Simba.Hua
 * 2017年8月27日
 **********************************************
 **/
public interface Request {
    public Object process() throws Exception;

    public Integer getProductId();

    public boolean isForceFefresh();
}