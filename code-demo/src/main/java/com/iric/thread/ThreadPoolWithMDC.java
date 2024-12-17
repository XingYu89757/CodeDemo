package com.iric.thread;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolWithMDC {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8,
            60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory()) {
        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return super.submit(wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return super.submit(wrap(task, MDC.getCopyOfContextMap()), result);
        }
    };

    public static ThreadPoolExecutor getPool() {
        return threadPoolExecutor;
    }

    private static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            Map<String, String> previous = MDC.getCopyOfContextMap();
            if (context != null) {
                MDC.setContextMap(context);
            }
            try {
                runnable.run();
            } finally {
                if (previous != null) {
                    MDC.setContextMap(previous);
                } else {
                    MDC.clear();
                }
            }
        };
    }

    private static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            Map<String, String> previous = MDC.getCopyOfContextMap();
            if (context != null) {
                MDC.setContextMap(context);
            }
            try {
                return callable.call();
            } finally {
                if (previous != null) {
                    MDC.setContextMap(previous);
                } else {
                    MDC.clear();
                }
            }
        };
    }
}

