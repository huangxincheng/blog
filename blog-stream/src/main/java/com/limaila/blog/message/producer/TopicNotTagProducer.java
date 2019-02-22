package com.limaila.blog.message.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class TopicNotTagProducer {


    @Value("${demo.rocketmq.topic}")
    private String springTopic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSendByStringMessage(String content) {
        SendResult sendResult = rocketMQTemplate.syncSend(springTopic, content);
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        return sendResult;
    }

    public SendResult syncSendBySpringMessage(String content) {
        SendResult sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload(content).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        return sendResult;
    }

}
