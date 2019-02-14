package com.limaila.blog.message.send;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class ProducerSend {

    @Autowired
    private MessageChannel output; // 获取name为output的binding

    public void send(String tag, String msg) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, tag);
        Message message = MessageBuilder.createMessage(msg, new MessageHeaders(headers));
        output.send(message);
    }
}
