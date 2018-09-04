package com.huashi.cloud.customer.common.domain.request;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 **********************************************
 *  描述：请求处理线程池，初始化队列数及每个队列最多能处理的数量
 * Simba.Hua
 * 2017年8月27日
 **********************************************
 **/
public class RequestProcessorThreadPool {
    private static final int blockingQueueNum = 10;
    private static final int queueDataNum = 100;
    private ExecutorService threadPool = Executors.newFixedThreadPool(blockingQueueNum);

    private RequestProcessorThreadPool() {
        for (int i = 0; i < blockingQueueNum; i++) {//初始化队列
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(queueDataNum);//每个队列中放100条数据
            RequestQueue.getInstance().addQueue(queue);
            threadPool.submit(new RequestProcessorThread<String>(queue));//把每个queue交个线程去处理，线程会处理每个queue中的数据
        }
    }

    public static class Singleton {
        private static volatile RequestProcessorThreadPool instance;
        public static RequestProcessorThreadPool getInstance() {
            if(instance == null) {
                synchronized (RequestProcessorThreadPool.class){
                    if(instance == null) {
                        new RequestProcessorThreadPool();
                    }
                }
            }
            return instance;
        }
    }

    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化线程池
     */
    public static void init() {
        getInstance();
    }
}
