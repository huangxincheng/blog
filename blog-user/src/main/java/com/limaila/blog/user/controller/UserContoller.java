package com.limaila.blog.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RequestMapping("/user")
@RestController
public class UserContoller {

    @RequestMapping
    public String index() {
        return "haha";
    }
}
