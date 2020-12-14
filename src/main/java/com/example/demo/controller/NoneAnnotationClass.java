package com.example.demo.controller;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: mq
 * @Date: 2020/7/14 17:29
 */
@MyAnnotation(value = "aa", name = "jajaj")
public class NoneAnnotationClass implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("1111111");
    }

    @MyAnnotation(value = "12232", name = "dfdfd")
    public void cheat() {

    }
}
