package com.iric.example.springbootrocketmq.simple.listener;



import com.alibaba.fastjson.JSONObject;
import com.iric.example.springbootrocketmq.simple.constant.RocketMqBizConstant;
import com.iric.example.springbootrocketmq.simple.domain.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 实体类消费监听器
 *
 * @author tianxincoord@163.com
 * @since 2022/5/12
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = RocketMqBizConstant.SOURCE_TOPIC,
        consumerGroup = RocketMqBizConstant.SOURCE_GROUP,
        selectorExpression = RocketMqBizConstant.SOURCE_TAG,
        // 指定消费者线程数，默认64，生产中请注意配置，避免过大或者过小
        consumeThreadMax = 5
)
public class RocketEntityMessageListener implements RocketMQListener<RocketMqMessage> {

    /**
     * 普通消息
     */
    @Override
    public void onMessage(RocketMqMessage message) {
        log.info("收到消息【{}】", JSONObject.toJSON(message));
        try {
            // 方法执行完成之后才会进行ack，否则将会重试
            TimeUnit.SECONDS.sleep(3);
            // 制造异常，将会自动进入重试队列
            // int ex = 10 / 0;
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("休眠了3s后消费完成");
    }
}
