package com.iric.abstractDemo;

import org.springframework.beans.BeansException;

public abstract class Parent {
    public void testMethod() {
        System.out.println("parent Method");
        refreshBeanFactory();
    }

    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;


    public static void main(String[] args) {
        Parent cat = new Cat();
        cat.testMethod();
    }
}
