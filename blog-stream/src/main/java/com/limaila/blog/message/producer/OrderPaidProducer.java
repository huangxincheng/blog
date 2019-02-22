package com.limaila.blog.message.producer;

import com.limaila.blog.message.domain.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class OrderPaidProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${demo.rocketmq.orderTopic}")
    private String orderPaidTopic;

    public void asyncSend(String orderId, BigDecimal paidMoney) {
        rocketMQTemplate.asyncSend(orderPaidTopic, new OrderPaidEvent(orderId, paidMoney), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("async onSucess SendResult=%s %n", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("async onException Throwable=%s %n", throwable);
            }
        });
    }
}
