package com.limaila.blog.message.receive;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@RocketMQMessageListener(topic = "${demo.rocketmq.msgExtTopic}", selectorExpression = "${demo.rocketmq.msgExtTag}", consumerGroup = "ext-msg-consumer")
public class ExtMsgConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt message) {
        System.out.printf("------- MessageExtConsumer received message, msgId: %s, body:%s \n", message.getMsgId(), new String(message.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // set consumer consume message from now
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}
