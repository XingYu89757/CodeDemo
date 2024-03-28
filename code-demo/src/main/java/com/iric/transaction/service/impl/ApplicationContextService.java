package com.iric.transaction.service.impl;


import com.iric.websocket.serverEndPoint.NoticeWebsocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ApplicationContextService implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Object noticeWebsocket = applicationContext.getBean(NoticeWebsocket.class);
        log.info("noticeWebsocket对象："+String.valueOf(noticeWebsocket.getClass()));
    }
}
