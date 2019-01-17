package com.limaila.blog.user.controller;

import com.limaila.blog.user.entity.User;
import com.limaila.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    UserService userService;

    @RequestMapping("/get/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userService.getOne(id);
    }
}
