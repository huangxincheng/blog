package com.limaila.blog.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.limaila.blog.user.entity.User;
import com.limaila.blog.user.feign.UserFeign;
import com.limaila.blog.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     ApiDoc 和 Swagger2 对比
 *      http://blog.51cto.com/13613194/2090764
*
 **/
@RestController
public class UserContoller implements UserFeign {

    @Autowired
    UserService userService;

    @Override
    @SentinelResource(blockHandler = "getBlockHandler", fallback = "getFallback")
    public User get(@PathVariable Integer id) {
        return userService.getOne(id);
    }


    public User getBlockHandler(Integer id, BlockException ex) {
        User user = new User();
        user.setId(-1);
        user.setUsername("Block");
        return user;
    }

    // fallback 函数 只有被降级才会调用
    public User getFallback(Integer id) {
        User user = new User();
        user.setId(-1);
        user.setUsername("Fallback");
        return user;
    }


}
