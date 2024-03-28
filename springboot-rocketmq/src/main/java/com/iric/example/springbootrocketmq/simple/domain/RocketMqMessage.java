package com.iric.example.springbootrocketmq.simple.domain;

import com.iric.example.springbootrocketmq.simple.config.RocketMqConfig;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * RocketMqMessage
 *
 * @author tianxincoord@163.com
 * @since 2022/5/12
 */
@Data
public class RocketMqMessage {
    private Long id;
    private String message;
    private String version;
    /**
     * LocalDate和LocalDateTime默认不支持，需要单独处理
     * {@link RocketMqConfig}
     */
    private LocalDate currentDate;
    private LocalDateTime currentDateTime;
}
