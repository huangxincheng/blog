package com.limaila.blog.cache.utils.string;

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
 **/
@Component
@Slf4j
public class RedisLockUtil {

    private static RedisTemplate<String, String> redisTemplate;

    private static final Integer SUCCESS = 1;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisLockUtil.redisTemplate = redisTemplate;
    }

    /**
     * 获取锁
     * @param lockKey 锁的key
     * @param clientId 加锁的客户端Id - 可采用UUID
     * @param expireTime 单位-秒
     * @return
     */

    public boolean getLock(String lockKey, String clientId, int expireTime) {
        String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
        String result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), clientId, expireTime);
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
        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
        String result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), clientId);
        if(SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
