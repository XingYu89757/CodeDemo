package com.iric.alarm.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.iric.abstractDemo.Cat;
import com.iric.alarm.AlarmInfo;
import com.iric.alarm.MessageAlarm;
import com.iric.mvcParam.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class WxExceptionMsgBotAlarm implements MessageAlarm {
    /**
     * 企微机器人webhook地址
     */
    @Value("${ExceptionMsgBot.url:https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=bd650041-fbb2-4584-b048-79cc3a685b0c}")
    private String url;

    private  String alarmMarkdownTemplate  = "{\n" +
            "    \"msgtype\": \"{}\",\n" +
            "    \"markdown\": {\n" +
            "        \"content\": \"组织架构同步成本中心异常，请相关同事注意。\"\n" +
            "         >类型:<font color=\"comment\">{}</font>\n" +
            "         >参数:<font color=\"comment\">{}</font>\n" +
            "         >异常信息:<font color=\"comment\">{}</font>\"\n" +
            "    }\n" +
            "}";

    private static String alarmTextTemplate = "{\n" +
            "    \"msgtype\": \"text\",\n" +
            "    \"text\": {\n" +
            "        \"content\": \"服务异常报错信息如下：{}\"\n" +
            "    }\n" +
            "}\n";


    @Override
    public boolean doAlarm( AlarmInfo alarmInfo) {
     String textMsg = buildAlarmInfo(alarmInfo);
        CompletableFuture.runAsync(()->{
            //发送消息
            HttpResponse response = HttpRequest
                    .post(url)
                    .header(Header.CONTENT_TYPE, "application/json; charset=UTF-8")
                    .body(textMsg)
                    .execute();
            log.info("【异常消息发送结果】 {}", response.body());
        });
        return false;
    }

    private String buildAlarmInfo(AlarmInfo alarmInfo) {
        return  StrUtil.format(alarmMarkdownTemplate, alarmInfo.getMsgType(), alarmInfo.getExecutorHandler(), alarmInfo.getExecutorParam(), alarmInfo.getErrorMessage());
    }


    // public static void main(String[] args) {
    //     Person person = new Person();
    //     person.setAddress("测试");
    //     person.setName("test");
    //     String format = StrUtil.format(alarmMarkdownTemplate, "markdown", "逻辑3更新名称", person.toString(), "空指针");
    //     System.out.println(format);
    // }


}
