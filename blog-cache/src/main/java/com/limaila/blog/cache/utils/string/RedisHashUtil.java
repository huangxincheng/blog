package com.limaila.blog.cache.utils.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Author: huangxincheng
 * Redis Hash 类型操作 util
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisHashUtil extends RedisBaseUtil {

    private static HashOperations<String, String, String> hashOperations;

    @Autowired
    public void setHashOperations(HashOperations<String, String, String> hashOperations) {
        RedisHashUtil.hashOperations = hashOperations;
    }

    public static Long hdel(String key, String ...hashKeys) {
        try {
            return hashOperations.delete(key, hashKeys);
        } catch (Exception e) {
            log.error("RedisHashUtil hdel", e);
            return null;
        }
    }

    public Boolean hasKey(String key, String hashKey) {
        try {
            return hashOperations.hasKey(key, hashKey);
        } catch (Exception e) {
            log.error("RedisHashUtil hasKey", e);
            return null;
        }
    }

    public static String hget(String key, String hashKey) {
        try {
            return hashOperations.get(key, hashKey);
        } catch (Exception e) {
            log.error("RedisHashUtil hget", e);
            return null;
        }
    }

    public static List<String> hmget(String key, Collection<String> hashKeys) {
        try {
            return hashOperations.multiGet(key, hashKeys);
        } catch (Exception e) {
            log.error("RedisHashUtil hmget");
            return null;
        }
    }

    public static List<String> hmget(String key, String ... hashKeys) {
        return hmget(key, Arrays.asList(hashKeys));
    }

    public static Long hincr(String key, String hashKey, long delta) {
        try {
            return hashOperations.increment(key, hashKey, delta);
        } catch (Exception e) {
            log.error("RedisHashUtil hincr");
            return null;
        }
    }

    public static Double hincr(String key, String hashKey, double delta) {
        try {
            return hashOperations.increment(key, hashKey, delta);
        } catch (Exception e) {
            log.error("RedisHashUtil hincr");
            return null;
        }
    }

    public static Long size(String key) {
        try {
            Long size = hashOperations.size(key);
            return size;
        } catch (Exception e) {
            log.error("RedisHashUtil size");
            return null;
        }
    }

    public static boolean putAll(String key, Map<String, String> map) {
        try {
            hashOperations.putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("RedisHashUtil putAll");
            return false;
        }
    }

    public static boolean put(String key, String hashKey, String value) {
        try {
            hashOperations.put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            log.error("RedisHashUtil put", e);
            return false;
        }
    }


    public static Boolean putIfAbsend(String key, String hashKey, String value) {
        try {
            return hashOperations.putIfAbsent(key, hashKey, value);
        } catch (Exception e) {
            log.error("RedisHashUtil putIfAbsend", e);
            return null;
        }
    }

    public static List<String> values(String key) {
        try {
            return hashOperations.values(key);
        } catch (Exception e) {
            log.error("RedisHashUtil values", e);
            return null;
        }
    }

    public static Map<String, String> entries(String key) {
        try {
            return hashOperations.entries(key);
        } catch (Exception e) {
            log.error("RedisHashUtil entries", e);
            return null;
        }
    }

    public static List<Map.Entry<String, String>> scan(String key) {
        List<Map.Entry<String, String>> entryList = new ArrayList<>();
        Cursor<Map.Entry<String, String>> curson = hashOperations.scan(key, ScanOptions.NONE);
        while (curson.hasNext()) {
            Map.Entry<String, String> entry = curson.next();
            entryList.add(entry);
        }
        return entryList;
    }
}
