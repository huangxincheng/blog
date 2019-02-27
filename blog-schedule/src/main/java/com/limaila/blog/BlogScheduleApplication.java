package com.limaila.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableScheduling //开启定时任务
@EnableAsync //开启异步任务
public class BlogScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogScheduleApplication.class, args);
    }
}
