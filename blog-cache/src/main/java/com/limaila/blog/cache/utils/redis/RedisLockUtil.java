package com.limaila.blog.cache.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     Redis 分布式锁 调用lua脚本
 **/
@Component
@Slf4j
public class RedisLockUtil {

    private static RedisTemplate<String, String> redisTemplate;

    private static final Long SUCCESS = 1L;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisLockUtil.redisTemplate = redisTemplate;
    }

    /**
     * 获取锁
     * @param lockKey 锁的key
     * @param clientId 加锁的客户端Id - 可采用UUID
     * @param expireSecond 失效时间 单位-秒
     *
     *                   PX 是毫秒
     *                   EX 是秒
     * @return
     */
    public static boolean getLockNotWait(String lockKey, String clientId, int expireSecond) {
//                String script = "if redis.call('set', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2]) then return 1 else return 0 end";
        RedisScript<Long> redisScript = RedisScript.of("if redis.call('set', KEYS[1], ARGV[1], 'NX', 'EX', ARGV[2]) then return 1 else return 0 end", Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), clientId, String.valueOf(expireSecond));
        if(SUCCESS.equals(result)){
           return true;
        }
        return false;
    }


    /**
     * 释放锁
     * @param lockKey 锁的key
     * @param clientId 加锁的客户端Id - 可采用UUID
     * @return
     */
    public static boolean releaseLock(String lockKey, String clientId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), clientId);
        if(SUCCESS == result) {
            return true;
        }
        return false;
    }
}
