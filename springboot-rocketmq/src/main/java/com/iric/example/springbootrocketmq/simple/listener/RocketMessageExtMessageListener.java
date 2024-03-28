package com.iric.example.springbootrocketmq.simple.listener;


import com.alibaba.fastjson.JSONObject;
import com.iric.example.springbootrocketmq.simple.constant.RocketMqBizConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 原始MessageExt消费监听器
 *
 * @author tianxincoord@163.com
 * @since 2022/5/12
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = RocketMqBizConstant.SOURCE_TOPIC,
        consumerGroup = "rocketmq_source_code_ext_group",
        selectorExpression = "rocketmq_source_code_ext_tag"
)
public class RocketMessageExtMessageListener implements RocketMQListener<MessageExt> {

    /**
     * MessageExt接收消息
     */
    @Override
    public void onMessage(MessageExt message) {
        log.info("收到消息【{}】", JSONObject.toJSON(message));
    }
}
