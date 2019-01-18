package com.limaila.blog.user.service;

import com.limaila.blog.user.dao.UserMapper;
import com.limaila.blog.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getOne(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }
}
