package com.iric.abstractDemo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

@Slf4j
public abstract class Parent {
    public void testMethod() {
        System.out.println("parent Method");
        refreshBeanFactory();
    }

    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;


    public static void main(String[] args) {
        Parent cat = new Cat();
        cat.testMethod();
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        Long next = snowflakeGenerator.next();
        log.info("生成id：{}",next);
    }
}
