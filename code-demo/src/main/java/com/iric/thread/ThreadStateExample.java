package com.iric.thread;


import com.iric.util.SleepUtils;
import org.apache.commons.lang3.ThreadUtils;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Yu.Xing
 * @Description  查看线程的各个状态。
 * 1.通过jps查看当前进程号
 * 2.jstack 查看当前线程的各个状态
 * @Date 10:21 2022/8/17
 **/
public class ThreadStateExample {
    public static void main(String[] args) {
        //查看线程的状态
//        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
//        new Thread(new Waiting(), "WaitingThread").start(); // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
//        new Thread(new Blocked(), "BlockedThread-1").start();
//        new Thread(new Blocked(), "BlockedThread-2").start();
        //测试守护线程特性
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
        synchronized (ThreadStateExample.class){
            SleepUtils.second(10);

        }

    }

    // 该线程不断地进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    /**
     * @Author Yu.Xing
     * @Description
     * 注意： Daemon属性需要在启动线程之前设置，不能在启动线程之后设置
     * Daemon线程被用作完成支持性工作，虚拟机需要推出的时，Java虚拟机中的所有Daemon线程都需要立即终止
     **/
    static  class DaemonRunner implements Runnable{

        @Override
        public void run() {
            System.out.println("DaemonThread start");
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("DaemonThread finally run.");
            }

        }
    }

}

