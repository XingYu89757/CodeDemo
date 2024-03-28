package com.iric.abstractDemo;

import org.springframework.beans.BeansException;

public class Cat extends Parent{
    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {
        System.out.println("cat");
    }
}
