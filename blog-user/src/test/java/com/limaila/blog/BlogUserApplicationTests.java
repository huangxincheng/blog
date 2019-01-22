package com.limaila.blog;

import com.alibaba.fastjson.JSON;
import com.limaila.blog.cache.utils.string.RedisLockUtil;
import com.limaila.blog.cache.utils.string.RedisValueUtil;
import com.limaila.blog.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogUserApplicationTests {

	@Autowired
	private RedisValueUtil redisUtil;

	@Autowired
	RedisLockUtil lockUtil;

	@Test
	public void contextLoads() {
//		String a = redisUtil.get("a");
//		System.out.println(a);
//        Double ab1 = redisUtil.decr("ab1", 0.5);
//        System.out.println(ab1);
//
//		boolean aklock = lockUtil.getLock("aklock", "1", 100);
//		boolean aklock1 = lockUtil.getLock("aklock", "2", 100);
//		System.out.println(aklock + ", " + aklock1);
		User user = new User();
		user.setActiveTime(new Date());
		user.setCreateTime(new Date());
		user.setEmail("249@qq.com");
		user.setId(1);
		user.setIsCanUse(true);
		user.setPassword("66500");
		user.setPhone("1326531572");
		user.setUsername("嘻嘻嘻");
		RedisValueUtil.set("user_1", JSON.toJSONString(user));
		String user_1 = RedisValueUtil.get("user_1");
		Long a1 = RedisValueUtil.incr("a1", 100);
		System.out.println(a1);
		System.out.println(user_1);
		User user1 = JSON.parseObject(user_1, User.class);
		System.out.println(JSON.toJSONString(user1));
	}

}

