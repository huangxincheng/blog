package com.limaila.blog;

import com.limaila.blog.cache.utils.string.RedisValueUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
//
//		User user_3 = RedisValueUtil.getToObject("user_3", User.class);
//		System.out.println(user_3);
	}

}

