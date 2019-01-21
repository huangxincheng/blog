package com.limaila.blog.cache.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisValueUtil {

    @Autowired
    private ValueOperations<String, String> valueOperations;

    public String get(String key) {
        try {
            return valueOperations.get(key);
        } catch (Exception e) {
            log.error("RedisUtil get", e);
            return null;
        }
    }

    public boolean set(String key, String value) {
        try {
            valueOperations.set(key, value);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil set", e);
            return false;
        }
    }

    public boolean setex(String key, String value, int expire) {
        try {
            valueOperations.set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil set", e);
            return false;
        }
    }

    public boolean setnx(String key, String value) {
        try {
            return valueOperations.setIfAbsent(key, value);
        } catch (Exception e) {
            log.error("RedisUtil setnx", e);
            return false;
        }
    }

    public Long incr(String key, long delta) {
        try {
            return valueOperations.increment(key, delta);
        } catch (Exception e) {
            log.error("RedisUtil incr", e);
            return null;
        }
    }


    public Double incr(String key, double delta) {
        try {
            return valueOperations.increment(key, delta);
        } catch (Exception e) {
            log.error("RedisUtil incr", e);
            return null;
        }
    }

    public Long decr(String key, long delta) {
        try {
            return valueOperations.increment(key, -delta);
        } catch (Exception e) {
            log.error("RedisUtil decr", e);
            return null;
        }
    }


    public Double decr(String key, double delta) {
        try {
            return valueOperations.increment(key, -delta);
        } catch (Exception e) {
            log.error("RedisUtil decr", e);
            return null;
        }
    }


    public List<String> mget(String... keys) {
        try {
            return valueOperations.multiGet(Arrays.asList(keys));
        } catch (Exception e) {
            log.error("RedisUtil mget", e);
            return null;
        }
    }

    public List<String> mget(Collection<String> keys) {
        try {
            return valueOperations.multiGet(keys);
        } catch (Exception e) {
            log.error("RedisUtil mget", e);
            return null;
        }
    }

    public boolean mset(Map<String, String> map) {
        try {
            valueOperations.multiSet(map);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil mset", e);
            return false;
        }
    }

    public String getSet(String key, String value) {
        try {
            return valueOperations.getAndSet(key, value);
        } catch (Exception e) {
            log.error("RedisUtil getSet", e);
            return null;
        }
    }


}
