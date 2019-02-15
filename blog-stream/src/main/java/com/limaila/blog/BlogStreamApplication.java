package com.limaila.blog;

import com.limaila.blog.message.send.ProducerSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;


/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     SpringCloud Stream For RockertMq
 **/
@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
public class BlogStreamApplication {

    private static ProducerSend producerSend;

    @Autowired
    public void setProducerSend(ProducerSend producerSend) {
        BlogStreamApplication.producerSend = producerSend;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogStreamApplication.class, args);
        System.out.println(producerSend);
    }
}