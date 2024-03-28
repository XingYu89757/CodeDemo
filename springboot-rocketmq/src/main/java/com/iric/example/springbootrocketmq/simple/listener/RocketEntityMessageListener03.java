package com.iric.example.springbootrocketmq.simple.listener;


import com.alibaba.fastjson.JSONObject;
import com.iric.example.springbootrocketmq.simple.constant.RocketMqBizConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * Object类型实体类消费监听器
 * 相同topic和tag监听下，会根据形参的类型来调用对应的listener
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
public class RocketEntityMessageListener03 implements RocketMQListener<Object> {

    @Override
    public void onMessage(Object message) {
        log.info("收到消息【{}】", JSONObject.toJSON(message));
    }
}
