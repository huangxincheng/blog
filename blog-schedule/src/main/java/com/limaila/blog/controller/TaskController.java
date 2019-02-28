package com.limaila.blog.controller;

import com.limaila.blog.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping
    public String index() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        asyncTask.t1();
        asyncTask.t2();
        asyncTask.t3();
        long t2 = System.currentTimeMillis();
        System.out.println("方法耗时：" + (t2 - t1));
        return  "方法耗时：" + (t2 - t1);
    }

    @RequestMapping("/v2")
    public String index2() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Future<String> f4 = asyncTask.t4();
        Future<String> f5 =asyncTask.t5();
        Future<String> f6 =asyncTask.t6();
        for(;;) {
            if (f4.isDone() && f5.isDone() && f6.isDone()) {
                long t2 = System.currentTimeMillis();
                System.out.println("方法耗时：" + (t2 - t1));
                return  "方法耗时：" + (t2 - t1);
            }
        }
    }
}
