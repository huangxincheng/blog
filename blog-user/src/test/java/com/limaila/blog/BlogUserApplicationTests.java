package com.limaila.blog;

import com.limaila.blog.cache.utils.RedisValueUtil;
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
		String a = redisUtil.get("a");
		System.out.println(a);
//        Double ab1 = redisUtil.decr("ab1", 0.5);
//        System.out.println(ab1);
    }

}

