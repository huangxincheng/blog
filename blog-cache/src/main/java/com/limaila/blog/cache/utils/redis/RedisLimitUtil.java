package com.limaila.blog.cache.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     Redis 分布式限流 调用lua脚本
 **/
@Component
@Slf4j
public class RedisLimitUtil {

    private static final String limitScript =
            "local key = KEYS[1] \n" +
            "local limit = tonumber(ARGV[1])\n" +
            "local curentLimit = tonumber(redis.call('get', key) or '0')\n" +
            "if curentLimit + 1 > limit then\n" +
            "    return 0;\n" +
            "else\n" +
            "    redis.call('INCRBY', key, 1)\n" +
            "    redis.call('EXPIRE', key, ARGV[2])\n" +
            "    return curentLimit + 1\n" +
            "end";


    private static RedisTemplate<String, String> redisTemplate;

    private static final Long FAIL = 0L;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisLimitUtil.redisTemplate = redisTemplate;
    }

    /**
     * 是否限流
     * @return
     *      true  已达到限流标准
     *      false 未达到限流标准
     */
    public static boolean isLimit(String limitKey, long limitNum, long expireSecond) {
        RedisScript<Long> redisScript = RedisScript.of(limitScript, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(limitKey), String.valueOf(limitNum), String.valueOf(expireSecond));
        if(FAIL.equals(result)){
            return true;
        }
        return false;
    }
}
