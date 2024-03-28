package com.iric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentMapDeadlock {
    public static void main(String[] args) {
        Map<String, Integer> concurrentHashMap1 = new ConcurrentHashMap<>(16);
        Map<String, Integer> concurrentHashMap2 = new ConcurrentHashMap<>(16);

        new Thread(() -> concurrentHashMap1.computeIfAbsent("a", key -> {
            concurrentHashMap2.computeIfAbsent("b", key2 -> 2);
            return 1;
        })).start();

        new Thread(() -> concurrentHashMap2.computeIfAbsent("b", key -> {
            concurrentHashMap1.computeIfAbsent("a", key2 -> 2);
            return 1;
        })).start();
    }
}
