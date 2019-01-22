package com.limaila.blog.cache.utils.redis;

import com.alibaba.fastjson.JSON;
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
 * Redis String 类型操作 util
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisValueUtil extends RedisBaseUtil {


    private static ValueOperations<String, String> valueOperations;

    @Autowired
    public void setValueOperations(ValueOperations<String, String> valueOperations) {
        RedisValueUtil.valueOperations = valueOperations;
    }

    public static String get(String key) {
        try {
            return valueOperations.get(key);
        } catch (Exception e) {
            log.error("RedisUtil get", e);
            return null;
        }
    }

    public static <T> T getToObject(String key, Class<T> clazz) {
        return JSON.parseObject(get(key), clazz);
    }

    public static <T> List<T> getToArray(String key, Class<T> clazz) {
        return JSON.parseArray(get(key), clazz);
    }


    public static boolean set(String key, String value) {
        try {
            valueOperations.set(key, value);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil set", e);
            return false;
        }
    }

    public static boolean set(String key, Object value) {
        return set(key, JSON.toJSONString(value));
    }

    public static boolean setex(String key, String value, int expire) {
        try {
            valueOperations.set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil set", e);
            return false;
        }
    }

    public static boolean setex(String key, Object value, int expire) {
        return setex(key, JSON.toJSONString(value), expire);
    }

    public static boolean setnx(String key, String value) {
        try {
            return valueOperations.setIfAbsent(key, value);
        } catch (Exception e) {
            log.error("RedisUtil setnx", e);
            return false;
        }
    }

    public static Long incr(String key, long delta) {
        try {
            return valueOperations.increment(key, delta);
        } catch (Exception e) {
            log.error("RedisUtil incr", e);
            return null;
        }
    }


    public static Double incr(String key, double delta) {
        try {
            return valueOperations.increment(key, delta);
        } catch (Exception e) {
            log.error("RedisUtil incr", e);
            return null;
        }
    }

    public static Long decr(String key, long delta) {
        return incr(key, -delta);
    }


    public static Double decr(String key, double delta) {
        return incr(key, -delta);
    }


    public static List<String> mget(String... keys) {
        return mget(Arrays.asList(keys));
    }

    public static List<String> mget(Collection<String> keys) {
        try {
            return valueOperations.multiGet(keys);
        } catch (Exception e) {
            log.error("RedisUtil mget", e);
            return null;
        }
    }

    public static boolean mset(Map<String, String> map) {
        try {
            valueOperations.multiSet(map);
            return true;
        } catch (Exception e) {
            log.error("RedisUtil mset", e);
            return false;
        }
    }

    public static String getSet(String key, String value) {
        try {
            return valueOperations.getAndSet(key, value);
        } catch (Exception e) {
            log.error("RedisUtil getSet", e);
            return null;
        }
    }


    public static Long size(String key) {
        try {
            return valueOperations.size(key);
        } catch (Exception e) {
            log.error("RedisUtil size", e);
            return null;
        }
    }

    public static Boolean msetnx(Map<String,String> map) {
        try {
            return valueOperations.multiSetIfAbsent(map);
        } catch (Exception e) {
            log.error("RedisUtil msetnx", e);
            return null;
        }
    }
}
