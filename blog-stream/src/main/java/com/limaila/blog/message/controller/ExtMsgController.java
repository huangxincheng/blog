package com.limaila.blog.message.controller;

import com.limaila.blog.message.producer.ExtMsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/ext/message")
public class ExtMsgController {

    @Autowired
    private ExtMsgProducer extMsgProducer;

    @RequestMapping("/{content}")
    public String index(@PathVariable String content) {
        extMsgProducer.send(content);
        return "OK";
    }
}
