package com.iric.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
/**
 * @Author Yu.Xing
 * @Description CompletableFuture 常用方法
 * 参考地址：
 * https://www.callicoder.com/java-executor-service-and-thread-pool-tutorial/
 **/
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runAsyncMethod();
        runAsyncMethodByLambda();
        //supplyAsyncMethod();
        //supplyAsyncByExecutor();
        //thenApplyMethod();
        //thenApplyMethod2();
        //thenAcceptMethod();
        //thenComposeMethod();
        //thenCombineMethod();
        //exceptionallyMethod();
        //threadException();
    }

    /**
     * @Author Yu.Xing
     * @Description 运行一个异步方法
     **/
    public static void runAsyncMethod() throws ExecutionException, InterruptedException {
        System.out.println("method is start");
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulate a long-running Job
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });
        // Block and wait for the future to complete
        future.get();
        System.out.println("method is end");
    }

    public static void runAsyncMethodByLambda() throws ExecutionException, InterruptedException {
        System.out.println("method is start");
        // Using Lambda Expression
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        future.get();
        System.out.println("method is end");
    }

    /**
     * @Author Yu.Xing
     * @Description Run a task asynchronously and return the result using
     **/
    public static void supplyAsyncMethod() throws ExecutionException, InterruptedException {
        // Run a task specified by a Supplier object asynchronously
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
    }

    public static void supplyAsyncMethodByLambda() throws ExecutionException, InterruptedException {
        // Using Lambda Expression
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
    }

    /**
     * @Author Yu.Xing
     * @Description 通过线程池执行任务
     **/
    public static void supplyAsyncByExecutor() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executor);
        // future.complete("end");
        System.out.println(future.get());
    }

    /**
     * @Author Yu.Xing
     * @Description thenApply() 可以使用 thenApply() 处理和改变CompletableFuture的结果
     **/
    public static void thenApplyMethod() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Rajeev
    }

    /**
     * @Author Yu.Xing
     * @Description 可以通过附加一系列的thenApply()在回调方法 在CompletableFuture写一个连续的转换。这样的话，结果中的一个 thenApply方法就会传递给该系列的另外一个 thenApply方法。
     **/
    public static void thenApplyMethod2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println(welcomeText.get());
        // Prints - Hello Rajeev, Welcome to the CalliCoder Blog
    }

    /**
     * @Author Yu.Xing
     * @Description如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept()和 thenRun()方法，
     * 这些方法经常在调用链的最末端的最后一个回调函数中使用。
     * CompletableFuture.thenAccept()持有一个Consumer<T>，返回一个CompletableFuture<Void>。它可以访问CompletableFuture的结果
     **/
    public static void thenAcceptMethod() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "tom";
        }).thenAccept(s -> {
            System.out.println("hello" + s);
        });

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "jerry";
        });
        System.out.println(completableFuture.get());
        System.out.println(completableFuture1.get());
    }

    /**
     * @Author Yu.Xing
     * @Description 组合两个独立的future 假设你想从一个远程API中获取一个用户的详细信息，一旦用户信息可用，你想从另外一个服务中获取他的相关信息
     **/
    public static void thenComposeMethod() throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = getName("tom").thenCompose(s -> {
            return getNameInfo(s);
        });
        System.out.println(result.get());
    }

    public static CompletableFuture<String> getName(String param) {
        return CompletableFuture.supplyAsync(() -> {
            return param;
        });
    }

    public static CompletableFuture<String> getNameInfo(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return "my name is " + name;
        });
    }

    /**
     * @Author Yu.Xing
     * @Description 使用thenCombine()组合两个独立的 future
     * 虽然thenCompose()被用于当一个future依赖另外一个future的时候用来组合两个future。thenCombine()被用来当两个独立的Future都完成的时候，用来做一些事情。
     **/
    public static void thenCombineMethod() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());
    }

    /**
     * @Author Yu.Xing
     * @Description 组合多个completableFuture
     * 假设需要下载100个页面的内容，串行操作的化，非常消耗时间，使用completableFuture异步处理
     **/
    public static void allOfMethod() {
        List<String> webPageLinks = Arrays.asList();    // A list of 100 web page links

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());


        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });
    }

    public static CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Code to download and return the web page's content
            return "";
        });
    }

    /**
     * @Author Yu.Xing
     * @Description 当任何一个CompletableFuture完成的时候【相同的结果类型】，返回一个新的CompletableFuture。以下示例：
     **/
    public static void anyOfMethod() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get()); // Result of Future 2

        /*
         * @Description 在以上示例中，当三个中的任何一个CompletableFuture完成， anyOfFuture就会完成。
         * 因为future2的休眠时间最少，因此她最先完成，最终的结果将是future2的结果。
         * CompletableFuture.anyOf()传入一个Future可变参数，
         * 返回CompletableFuture。CompletableFuture.anyOf()的问题是如果你的CompletableFuture返回的结果是不同类型的，
         * 这时候你讲会不知道你最终CompletableFuture是什么类型。
         */
    }

    /**
     * @Author Yu.Xing
     * @Description 使用 exceptionally() 回调处理异常 exceptionally()回调给你一个从原始Future中生成的错误恢复的机会。你可以在这里记录这个异常并返回一个默认值。
     **/
    public static void exceptionallyMethod() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    /**
     * @Author Yu.Xing
     * @Description 使用 handle() 方法处理异常 API提供了一个更通用的方法 - handle()从异常恢复，无论一个异常是否发生它都会被调用。
     **/
    public static void handleMethod() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    /**
     * @Author Yu.Xing
     * @Description 测试多线程情况下，主线程是否可以捕捉到子线程抛出的异常
     **/
    public static void threadException() {
        System.out.println("method start");
        System.out.println("oldThread name:" + Thread.currentThread().getName());
        try {
            //创建10条线程
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    //获取当前时间的最后一位数，如果为8，调用方法抛出异常
                    long l = System.nanoTime();
                    String str = Long.toString(l);
                    String substring = str.substring(str.length() - 3, str.length() - 2);
                    System.out.println("time:" + substring + "newThread:" + Thread.currentThread().getName());
                    if (substring.equals("8")) {
                        testException();
                    }
                }).start();
            }
        } catch (Exception e) {
            System.out.println("catch error");
            e.printStackTrace();
        }

        System.out.println("method finish");
    }

    public static void testException() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("error");
        throw new RuntimeException("test Exception");
    }

}
