package com.iric.example.springbootrocketmq.simple.constant;

/**
 * RocketMQ事件组常量
 *
 *
 * @author tianxincoord@163.com
 * @since 2022/5/18
 */
public interface RocketMqBizConstant {
    /**
     * 先在服务器创建好TOPIC
     */
    String SOURCE_TOPIC = "rocketmq_source_code_topic";
    String SOURCE_GROUP = "rocketmq_source_code_group";
    String SOURCE_TAG = "rocketmq_source_code_tag";
}
