package com.limaila.blog.cache.utils.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static Boolean expire(String key, long timeout) {
        try {
            return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("RedisBaseUtil expire", e);
            return null;
        }
    }

    public static Boolean expireAt(String key, Date date) {
        try {
            return redisTemplate.expireAt(key, date);
        } catch (Exception e) {
            log.error("RedisBaseUtil expireAt", e);
            return null;
        }
    }

    public static DataType type(String key) {
        try {
            DataType type = redisTemplate.type(key);
            return type;
        } catch (Exception e) {
            log.error("RedisBaseUtil type", e);
            return null;
        }
    }

    public static Boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("RedisBaseUtil exists", e);
            return null;
        }
    }

    public static Long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key);
        } catch (Exception e) {
            log.error("RedisBaseUtil getExpire", e);
            return null;
        }
    }
}
