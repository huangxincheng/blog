package com.limaila.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.limaila.blog.**.dao"})
public class BlogUserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(BlogUserApplication.class, args);
//		while(true) {
//			// 当修改nacos时候可以动态的看到值已经发生变化
//			String userName = cac.getEnvironment().getProperty("user.name");
//			System.out.println(userName);
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

}

