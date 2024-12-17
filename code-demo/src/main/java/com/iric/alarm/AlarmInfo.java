package com.iric.alarm;

import lombok.Data;

@Data
public class AlarmInfo {

    private static final String MARKDOWN = "markdown";
    private static final String TEXT ="text";
    /**
     * 异常数据id
     */
    private Long id;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 提醒文本
     */
    private String alarmContent;

    /**
     * 报错消息
     */
    private String errorMessage;

    /**
     * 执行中的程序
     */
    private String executorHandler;

    /**
     * 执行参数
     */
    private String executorParam;


}
