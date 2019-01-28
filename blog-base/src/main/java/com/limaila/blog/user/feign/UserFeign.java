package com.limaila.blog.user.feign;

import com.limaila.blog.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@FeignClient(value = "blog-user")
public interface UserFeign {

    @GetMapping("/user/get/{id}")
    User get(@PathVariable("id") Integer id);
}
