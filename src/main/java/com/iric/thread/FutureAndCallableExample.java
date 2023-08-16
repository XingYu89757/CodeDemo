package com.iric.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author Yu.Xing
 * @Description Callable和future的常用方法demo
 * 参考来源：https://www.callicoder.com/java-callable-and-future-tutorial/
 **/
public class FutureAndCallableExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // futureCallable();
         futureIsDone();
        // futureCancel();
        //invokeAll();
        //invokeAny();
    }

    /**
     * @Author Yu.Xing
     * @Description 异步接收响应
     **/
    public static void futureCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            // Perform some computation
            System.out.println("Entered Callable");
            Thread.sleep(3000);
            return "Hello from Callable";
        };

        System.out.println("Submitting Callable");
        Future<String> future = executorService.submit(callable);

        // This line executes immediately
        System.out.println("Do something else while callable is getting executed");

        System.out.println("Retrieve the result of the future");
        // Future.get() blocks until the result is available
        String result = future.get();

        //The future.get() method will throw a TimeoutException if the task is not completed within the specified time.
        //future.get(1,TimeUnit.SECONDS);

        System.out.println(result);

        executorService.shutdown();
    }

    public static void futureRunnable(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            System.out.println("Entered Callable");
        };
        Future<String> thisIsResult = executorService.submit(runnable, "this is result");


    }


    /**
     * @Author Yu.Xing
     * @Description 判断线程是否正在运行中
     **/
    public static void futureIsDone() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello from Callable";
        });

        while (!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
        }

        System.out.println("Task completed! Retrieving the result");
        String result = future.get();
        System.out.println(result);

        executorService.shutdown();
    }

    /**
     * @Author Yu.Xing
     * @Description 线程手动取消
     **/
    public static void futureCancel() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello from Callable";
        });

        while (!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.0;

            if (elapsedTimeInSec > 1) {
                future.cancel(true);
            }
        }

        System.out.println("Task completed! Retrieving the result");
        //check the future is cancelled
        if (!future.isCancelled()) {
            System.out.println("Task completed! Retrieving the result");
            String result = future.get();
            System.out.println(result);
        }
        executorService.shutdown();
    }

    /**
     * @Author Yu.Xing
     * @Description 一次启动多个线程并且获取线程的返回值
     **/
    public static void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "Result of Task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "Result of Task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "Result of Task3";
        };

        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);

        List<Future<String>> futures = executorService.invokeAll(taskList);

        for (Future<String> future : futures) {
            // The result is printed only after all the futures are complete. (i.e. after 5 seconds)
            System.out.println(future.get());
        }

        executorService.shutdown();
    }

    /**
     * @Author Yu.Xing
     * @Description 一次启动多个线程，返回最快完成的结果
     **/
    public static void invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "Result of Task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "Result of Task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "Result of Task3";
        };

        // Returns the result of the fastest callable. (task2 in this case)
        String result = executorService.invokeAny(Arrays.asList(task1, task2, task3));

        System.out.println(result);
        executorService.shutdown();
    }



}
