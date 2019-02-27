package com.limaila.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableScheduling
public class BlogScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogScheduleApplication.class, args);
    }
}
