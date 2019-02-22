package com.limaila.blog.message.controller;

import com.limaila.blog.message.producer.OrderPaidProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/order/paid/message")
public class OrderPaidMessageController {

    @Autowired
    private OrderPaidProducer orderPaidProducer;

    @RequestMapping("/{orderId}/{paidMoney}")
    public String index(@PathVariable String orderId, @PathVariable BigDecimal paidMoney) {
        orderPaidProducer.asyncSend(orderId, paidMoney);
        return "OK";
    }
}
