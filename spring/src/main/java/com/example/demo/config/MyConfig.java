package com.example.demo.config;

import org.springframework.context.annotation.PropertySource;

/**
 * @Author: mq
 * @Date: 2020/12/30 16:13
 */
@PropertySource(name = "name", value = {"aaaa", "bbbbb"})
public class MyConfig {
    private String name;

}
