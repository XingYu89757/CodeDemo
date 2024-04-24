package com.iric.example.springbootrocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRocketmqApplication {

    public static void main(String[] args) {
        /*
         * 指定使用的日志框架，否则将会告警
         * RocketMQLog:WARN No appenders could be found for logger (io.netty.util.internal.InternalThreadLocalMap).
         * RocketMQLog:WARN Please initialize the logger system properly.
         */
        System.setProperty("rocketmq.client.logUseSlf4j", "true");

        SpringApplication.run(SpringbootRocketmqApplication.class, args);
    }

}
