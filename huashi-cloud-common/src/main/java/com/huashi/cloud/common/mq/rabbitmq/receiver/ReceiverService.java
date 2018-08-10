package com.huashi.cloud.common.mq.rabbitmq.receiver;

import com.huashi.cloud.common.mq.rabbitmq.model.Bar;
import com.huashi.cloud.common.mq.rabbitmq.model.Foo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liangkun on 2018-7-25.
 */
//@Component
public class ReceiverService {

//    @RabbitListener(queues = "queue.foo")
//    public void receiveFooQueue(Foo foo) {
//        System.out.println("Received Foo<" + foo.getName() + ">");
//    }
//
//    @RabbitListener(queues = "queue.bar")
//    public void receiveBarQueue(Bar bar) {
//        System.out.println("Received Bar<" + bar.getAge() + ">");
//    }
}
