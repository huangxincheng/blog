package com.limaila.blog.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class AsyncTask {

    @Async
    public void t1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(1000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t1耗时" + (t2 - t1));
    }

    @Async
    public void t2() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t2耗时" + (t2 - t1));
    }

    @Async
    public void t3() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(3000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t3耗时" + (t2 - t1));
    }

    @Async
    public Future<String> t4() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(1000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t4耗时" + (t2 - t1));
        return new AsyncResult<>("t4耗时" + (t2 - t1));
    }

    @Async
    public Future<String> t5() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t5耗时" + (t2 - t1));
        return new AsyncResult<>("t5耗时" + (t2 - t1));
    }


    @Async
    public Future<String> t6() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(3000L);
        long t2 = System.currentTimeMillis();
        System.out.println("t6耗时" + (t2 - t1));
        int u = 1 / 0;
        System.out.println("t6耗时异常" + (t2 - t1));
        return new AsyncResult<>("t6耗时" + (t2 - t1));
    }



}
