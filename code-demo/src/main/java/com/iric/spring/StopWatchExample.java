package com.iric.spring;

import org.springframework.util.StopWatch;

public class StopWatchExample {
    // public static void main(String[] args) throws InterruptedException {
    //     StopWatch stopWatch = new StopWatch();
    //
    //     // 任务一模拟休眠3秒钟
    //     stopWatch.start("TaskOneName");
    //     Thread.sleep(1000 * 3);
    //     System.out.println("当前任务名称：" + stopWatch.currentTaskName());
    //     stopWatch.stop();
    //
    //     // 任务一模拟休眠10秒钟
    //     stopWatch.start("TaskTwoName");
    //     Thread.sleep(1000 * 10);
    //     System.out.println("当前任务名称：" + stopWatch.currentTaskName());
    //     stopWatch.stop();
    //
    //     // 任务一模拟休眠10秒钟
    //     stopWatch.start("TaskThreeName");
    //     Thread.sleep(1000 * 10);
    //     System.out.println("当前任务名称：" + stopWatch.currentTaskName());
    //     stopWatch.stop();
    //
    //     // 打印出耗时
    //     System.out.println(stopWatch.prettyPrint());
    //     System.out.println(stopWatch.shortSummary());
    //     // stop后它的值为null
    //     System.out.println(stopWatch.currentTaskName());
    //
    //     // 最后一个任务的相关信息
    //     System.out.println(stopWatch.getLastTaskName());
    //     System.out.println(stopWatch.getLastTaskInfo());
    //
    //     // 任务总的耗时  如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
    //     // System.out.println("所有任务总耗时：" + sw.getTotalTimeMillis());
    //     // System.out.println("任务总数：" + sw.getTaskCount());
    //     // System.out.println("所有任务详情：" + sw.getTaskInfo());
    // }

    public static void main(String[] args) throws InterruptedException {
        exampe();
    }

    public static void exampe() throws InterruptedException {

        StopWatch stopWatch = new StopWatch("任务耗时秒表工具");

        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        stopWatch.start("task2");
        Thread.sleep(3000);
        stopWatch.stop();
        // 所有任务耗时时间
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch("任务耗时秒表工具2");
        stopWatch2.start("task3");
        Thread.sleep(3000);
        stopWatch2.stop();
        // 所有任务耗时时间
        System.out.println(stopWatch2.getTotalTimeMillis());
        System.out.println(stopWatch2.prettyPrint());

    }
}
