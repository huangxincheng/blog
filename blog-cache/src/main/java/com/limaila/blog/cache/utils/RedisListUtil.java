package com.limaila.blog.cache.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author: huangxincheng
 * Redis List 类型操作 util
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class RedisListUtil {

    private static ListOperations<String, String> listOperations;

    @Autowired
    public void setListOperations(ListOperations<String, String> listOperations) {
        RedisListUtil.listOperations = listOperations;
    }

    public static Long lpush(String key, String value) {
        try {
            return listOperations.leftPush(key, value);
        } catch (Exception e) {
            log.error("RedisListUtil lpush", e);
            return null;
        }
    }

    /**
     * 将value值放在pivot值得左边
     * @param key key
     * @param pivot pivot
     * @param value value
     * @return
     */
    public static Long lpush(String key, String pivot, String value ) {
        try {
            return listOperations.leftPush(key, pivot, value);
        } catch (Exception e) {
            log.error("RedisListUtil lpush", e);
            return null;
        }
    }


    public static String lpop(String key) {
        try {
            return listOperations.leftPop(key);
        } catch (Exception e) {
            log.error("RedisListUtil lpop", e);
            return null;
        }
    }

    public static String blpop(String key, long timeout) {
        try {
            return listOperations.leftPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("RedisListUtil blpop", e);
            return null;
        }
    }

    public static Long lpushAll(String key, String ... values) {
        try {
            return listOperations.leftPushAll(key, values);
        } catch (Exception e) {
            log.error("RedisListUtil lpushAll", e);
            return null;
        }
    }

    public static Long lpushAll(String key, Collection<String> values) {
        try {
            return listOperations.leftPushAll(key, values);
        } catch (Exception e) {
            log.error("RedisListUtil lpushAll", e);
            return null;
        }
    }


    public static Long lpushIfPresent(String key, String value) {
        try {
            return listOperations.leftPushIfPresent(key, value);
        } catch (Exception e) {
            log.error("RedisListUtil lpushIfPresent", e);
            return null;
        }
    }


    public static Long rpush(String key, String value) {
        try {
            return listOperations.rightPush(key, value);
        } catch (Exception e) {
            log.error("RedisListUtil rpush", e);
            return null;
        }
    }


    /**
     * 将value值放在pivot值得右边
     * @param key key
     * @param pivot pivot
     * @param value value
     * @return
     */
    public static Long rpush(String key, String pivot, String value ) {
        try {
            return listOperations.rightPush(key, pivot, value);
        } catch (Exception e) {
            log.error("RedisListUtil rpush", e);
            return null;
        }
    }


    public static String rpop(String key) {
        try {
            return listOperations.rightPop(key);
        } catch (Exception e) {
            log.error("RedisListUtil rpop", e);
            return null;
        }
    }

    public static String brpop(String key, long timeout) {
        try {
            return listOperations.rightPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("RedisListUtil brpop", e);
            return null;
        }
    }


    public static Long rpushAll(String key, String ... values) {
        try {
            return listOperations.rightPushAll(key, values);
        } catch (Exception e) {
            log.error("RedisListUtil rpushAll", e);
            return null;
        }
    }

    public static Long rpushAll(String key, Collection<String> values) {
        try {
            return listOperations.rightPushAll(key, values);
        } catch (Exception e) {
            log.error("RedisListUtil rpushAll", e);
            return null;
        }
    }

    public static Long rpushIfPresent(String key, String value) {
        try {
            return listOperations.rightPushIfPresent(key, value);
        } catch (Exception e) {
            log.error("RedisListUtil rpushIfPresent", e);
            return null;
        }
    }


    public static List<String> lrange(String key, long start, long end) {
        try {
            return listOperations.range(key, start, end);
        } catch (Exception e) {
            log.error("RedisListUtil lrange", e);
            return null;
        }
    }

    /**
     * lrem [lrem key count value] :移除等于value的元素，
     * 当count>0时，从表头开始查找，移除count个 移除等于value的元素；
     * 当count=0时，从表头开始查找，移除所有等于value的；
     * 当count<0时，从表尾开始查找，移除count个 移除等于value的元素。
     * @param key key
     * @param count count
     * @param value value
     * @return
     */
    public static Long lrem(String key, long count, String value) {
        try {
            return listOperations.remove(key, count, value);
        } catch (Exception e) {
            log.error("RedisListUtil lrem", e);
            return null;
        }
    }


    /**
     * 根据下表获取列表中的值，下标是从0开始的
     * @param key key
     * @param index index
     * @return
     */
    public static String lindex(String key, long index) {
        try {
            return listOperations.index(key, index);
        } catch (Exception e) {
            log.error("RedisListUtil lindex", e);
            return null;
        }
    }

    /**
     * 将value的值设置到列表index位置
     * 数组不能越界
     * @param key key
     * @param index index
     * @param value value
     * @return
     */
    public static boolean lset(String key, long index, String value) {
        try {
            listOperations.set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("RedisListUtil lset", e);
            return false;
        }
    }

    /**
     * 修剪现有列表，使其只保留指定的指定范围的元素，起始和停止都是基于0的索引
     * @param key key
     * @param start start
     * @param end end
     * @return
     */
    public static boolean ltrim(String key, long start, long end) {
        try {
            listOperations.trim(key, start, end);
            return true;
        } catch (Exception e) {
            log.error("RedisListUtil ltrim", e);
            return false;
        }
    }
}
