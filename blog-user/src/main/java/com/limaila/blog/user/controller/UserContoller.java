package com.limaila.blog.user.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
    @SentinelResource(blockHandler = "getBlockHandler",fallback = "getFallback")
    public User get(@PathVariable("id") Integer id) {
//        Entry entry = null;
//        try {
//            entry = SphU.entry("UserContoller_get");
//            // 真正的业务逻辑.
//            return userService.getOne(id);
//        } catch (BlockException ex) {
//            // 处理限流降级异常
//            System.out.println("ex" + ex.getMessage());
//            User user = new User();
//            user.setId(-1);
//            return user;
//        } finally {
//            if (entry != null) {
//                entry.exit();
//            }
//        }
        return userService.getOne(id);
    }

    // blockHandler 函数，原方法调用被流控
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
