package com.limaila.blog.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableAdminServer
public class BlogAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminServerApplication.class, args);
    }
}
