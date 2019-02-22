package com.limaila.blog.message.receive;

import com.limaila.blog.message.domain.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@RocketMQMessageListener(topic = "*", consumerGroup = "order-paid-consumer")
public class OrderPailConsumer implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent message) {
        System.out.printf("------- OrderPaidEventConsumer received: %s \n", message);
    }
}
