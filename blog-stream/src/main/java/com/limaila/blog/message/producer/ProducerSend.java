package com.limaila.blog.message.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class ProducerSend {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void syncSendByString(String topic, Object content) {
        rocketMQTemplate.syncSend(topic, content);
    }

    public void syncSendByMessage(String topic, Object content) {
        rocketMQTemplate.syncSend(topic,  MessageBuilder.withPayload(content).build());
    }

    public void asyncSend(String topic, Object content, SendCallback sendCallback) {
        rocketMQTemplate.asyncSend(topic, content, sendCallback);
    }

    public void send(String topic, String tag, Object content) {
        rocketMQTemplate.convertAndSend(topic + ":" + tag, content);
    }

    public void sendTransactionMessage(String group, String topic, String tag, Object content) {
        rocketMQTemplate.sendMessageInTransaction(group, topic + ":" + tag, MessageBuilder.withPayload(content).build(), null);
    }

//    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
//        //Instantiate with a producer group name.
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        // Specify name server addresses.
//        producer.setNamesrvAddr("47.106.95.198:9876");
//        producer.setSendMsgTimeout(1000000000);
//        //Launch the instance.
//        producer.start();
//        for (int i = 0; i < 100; i++) {
//            //Create a message instance, specifying topic, tag and message body.
//            Message msg = new Message("TopicTest" /* Topic */,
//                    "TagA" /* Tag */,
//                    ("Hello World " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            //Call producer message to deliver message to one of brokers.
//            SendResult sendResult = producer.producer(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        //Shut down once the producer instance is not longer in use.
//        producer.shutdown();
//    }

//    public static void main(String[] args) throws InterruptedException, MQClientException {
//
//        // Instantiate with specified consumer group name.
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
//
//        // Specify name server addresses.
//        consumer.setNamesrvAddr("47.106.95.198:9876");
//
//        // Subscribe one more more topics to consume.
//        consumer.subscribe("TopicTest", "*");
//        // Register callback to execute on arrival of messages fetched from brokers.
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                            ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        //Launch the consumer instance.
//        consumer.start();
//
//        System.out.printf("Consumer Started.%n");
//    }
}
