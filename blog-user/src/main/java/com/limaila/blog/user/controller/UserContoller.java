package com.limaila.blog.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.limaila.blog.user.entity.User;
import com.limaila.blog.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     ApiDoc 和 Swagger2 对比
 *      http://blog.51cto.com/13613194/2090764
*
 **/
@RequestMapping("/user")
@RestController
@Api("用户相关Api")
public class UserContoller {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "根据用户的id获取用户信息", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @GetMapping("/get/{id}")
    @SentinelResource("UserContoller.get")
    public User get(@PathVariable("id") Integer id) {
        return userService.getOne(id);
    }
}
