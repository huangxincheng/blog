package com.limaila.blog.message.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class ExtMsgProducer {

    @Value("${demo.rocketmq.msgExtTopic}")
    private String msgExtTopic;

    @Value("${demo.rocketmq.msgExtTag}")
    private String msgExtTag;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(String content) {
        rocketMQTemplate.convertAndSend(msgExtTopic + ":" + msgExtTopic, content);
    }
}
