package com.iric.thread.test;

import com.iric.thread.ThreadPoolWithMDC;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ThreadPoolWithMDCTest {

    public static void main(String[] args) {
        // 获取线程池实例
        ThreadPoolExecutor executor = ThreadPoolWithMDC.getPool();

        // 设置主线程的MDC信息
        MDC.put("traceId", "12345");
        log.info("设置treceID");
        // 提交任务到线程池
        executor.submit(() -> {
            // 获取子线程中的traceId
            String traceId = MDC.get("traceId");
            System.out.println("TraceId in thread: " + traceId);

           log.info("线程池中的traceID");
        });

        log.info("主线程中的traceID");
        // 清理MDC
        MDC.clear();

        // 关闭线程池
        executor.shutdown();
    }
}

