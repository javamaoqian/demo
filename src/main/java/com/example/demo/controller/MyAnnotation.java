package com.example.demo.controller;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: mq
 * @Date: 2020/7/6 18:06
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {
    @AliasFor("code")
    String value() default "";

    @AliasFor("value")
    String code() default "";

    String name() default "";
}
