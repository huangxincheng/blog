package com.limaila.blog;

import com.limaila.blog.cache.utils.string.RedisLimitUtil;
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

	@Test
	public void contextLoads() {
//		User user = new User();
//		user.setActiveTime(new Date());
//		user.setCreateTime(new Date());
//		user.setEmail("249@qq.com");
//		user.setId(3);
//		user.setIsCanUse(true);
//		user.setPassword("66500");
//		user.setPhone("1326531572");
//		user.setUsername("嘻嘻嘻");
//		RedisValueUtil.set("user_3", user);
//		System.out.println(1);
//
//		User user_3 = RedisValueUtil.getToObject("user_3", User.class);
//		System.out.println(user_3);
//		RedisLockUtil.getLock("ak", "uuid_1", 1000);
//		RedisLockUtil.getLock("ak", "uuid_2", 1000);

//		RedisLockUtil.releaseLock("akL1", "2");
//		RedisLockUtil.releaseLock("akL1", "1");

//		String clientId = UUID.randomUUID().toString();
//		if (RedisLockUtil.getLock("ak1", clientId, 1000)) {
//			try {
//				System.out.println("嘿嘿 getLock");
//			} finally {
//				RedisLockUtil.releaseLock("ak1", clientId);
//				System.out.println("嘿嘿 releaseLock");
//			}
//		}

		boolean limitKey = RedisLimitUtil.isLimit("limitKey", 5, 1000);
		System.out.println(limitKey);
		for (int i = 1 ; i < 10; i ++) {
			boolean limitKey1 = RedisLimitUtil.isLimit("limitKey", 5, 1000);
			System.out.println(limitKey1);
		}


	}

}

