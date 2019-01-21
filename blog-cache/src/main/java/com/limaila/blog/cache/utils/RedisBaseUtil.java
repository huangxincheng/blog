package com.limaila.blog.cache.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisBaseUtil {


    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisBaseUtil.redisTemplate = redisTemplate;
    }

    public static Boolean delKey(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("RedisBaseUtil del", e);
            return null;
        }
    }

    public static Long delKeys(String ... keys) {
        return delKeys(Arrays.asList(keys));
    }

    public static Long delKeys(Collection<String> keys) {
        try {
            return redisTemplate.delete(keys);
        } catch (Exception e) {
            log.error("RedisBaseUtil delKeys");
            return null;
        }
    }
}
