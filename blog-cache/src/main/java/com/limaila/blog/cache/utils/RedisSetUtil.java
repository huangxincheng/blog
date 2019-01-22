package com.limaila.blog.cache.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Author: huangxincheng
 * Redis Set 类型操作 util
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisSetUtil extends RedisBaseUtil {

    private static SetOperations<String, String> setOperations;

    @Autowired
    public void setSetOperations(SetOperations<String, String> setOperations) {
        RedisSetUtil.setOperations = setOperations;
    }

    private static Long add(String key, String ...values) {
        try {
            return setOperations.add(key, values);
        } catch (Exception e) {
            log.error("RedisSetUtil add", e);
            return null;
        }
    }

    private static Long remove(String key, String values) {
        try {
            return setOperations.remove(key, values);
        } catch (Exception e) {
            log.error("RedisSetUtil remove", e);
            return null;
        }
    }

    private static String pop(String key) {
        try {
            return setOperations.pop(key);
        } catch (Exception e) {
            log.error("RedisSetUtil pop", e);
            return null;
        }
    }

    private static List<String> pop(String key, long count) {
        try {
            return setOperations.pop(key, count);
        } catch (Exception e) {
            log.error("RedisSetUtil pop", e);
            return null;
        }
    }

    private static Boolean move(String key, String value, String destKey) {
        try {
            return setOperations.move(key, value, destKey);
        } catch (Exception e) {
            log.error("RedisSetUtil move", e);
            return null;
        }
    }

    private static Long size(String key) {
        try {
            return setOperations.size(key);
        } catch (Exception e) {
            log.error("RedisSetUtil size", e);
            return null;
        }
    }

    private static Boolean isMember(String key, Object o) {
        try {
            return setOperations.isMember(key, o);
        } catch (Exception e) {
            log.error("RedisSetUtil isMember", e);
            return null;
        }
    }


    private static Set<String> difference(String key, String otherKey) {
        try {
            return setOperations.difference(key, otherKey);
        } catch (Exception e) {
            log.error("RedisSetUtil difference", e);
            return null;
        }
    }

}
