package com.iric.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 等待多线程完成的CountDownLatch
 * 假如有这样一个需求：我们需要解析一个Excel里多个sheet的数据，此时可以考虑使用多
 * 线程，每个线程解析一个sheet里的数据，等到所有的sheet都解析完之后，程序需要提示解析完
 * 成。在这个需求中，要实现主线程等待所有线程完成sheet的解析操作，最简单的做法是使用
 * join()方法，
 */
public class CountDownLatchExample {

    public static void JoinCountDownLatchTest() throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }

    static CountDownLatch c = new CountDownLatch(2);

    public static void countDownLatchTest() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println("3");
    }

}
