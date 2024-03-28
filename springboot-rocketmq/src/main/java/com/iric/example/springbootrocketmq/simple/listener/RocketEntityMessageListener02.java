package com.iric.example.springbootrocketmq.simple.listener;


import com.alibaba.fastjson.JSONObject;
import com.iric.example.springbootrocketmq.simple.constant.RocketMqBizConstant;
import com.iric.example.springbootrocketmq.simple.domain.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * 相同实体类消费监听器
 * 同一个topic和tag可以有多个监听器，相当于同一个group下的消费者集群
 * 消息只会被其中一个进行消费
 *
 * @author tianxincoord@163.com
 * @since 2022/5/12
 */
@Slf4j
// @Component
@RocketMQMessageListener(
        topic = RocketMqBizConstant.SOURCE_TOPIC,
        consumerGroup = RocketMqBizConstant.SOURCE_GROUP,
        selectorExpression = RocketMqBizConstant.SOURCE_TAG
)
public class RocketEntityMessageListener02 implements RocketMQListener<RocketMqMessage> {

    /**
     * 普通消息
     */
    @Override
    public void onMessage(RocketMqMessage message) {
        log.info("收到消息【{}】", JSONObject.toJSON(message));
    }
}
