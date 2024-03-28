package com.iric.jdk8_demo;

import java.time.Instant;
import java.time.LocalDateTime;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime startOfYesterday = yesterday.withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfYesterday = yesterday.withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        System.out.println("昨天的开始时间：" + startOfYesterday);
        System.out.println("昨天的结束时间：" + endOfYesterday);
        Instant now1 = Instant.now();
    }
}
