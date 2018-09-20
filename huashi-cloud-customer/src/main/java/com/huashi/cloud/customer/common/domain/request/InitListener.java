package com.huashi.cloud.customer.common.domain.request;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 **********************************************
 *  描述：spring 启动初始化线程池类
 * Simba.Hua
 * 2017年8月27日
 **********************************************
 **/
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // TODO Auto-generated method stub
        if (event.getApplicationContext().getParent() != null) {
            return;
        }
        RequestProcessorThreadPool.init();
    }
}

