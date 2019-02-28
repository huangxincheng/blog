package com.limaila.blog.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class TimerTask {

    /**
     * 2秒执行一次
     */
//    @Scheduled(fixedRate = 2000)
    public void foo() {
        System.out.println("嘿嘿 :" + System.currentTimeMillis());
    }
}
