package com.limaila.blog.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getServerPort")
    public String getServerPort() {
        return this.serverPort;
    }

}
