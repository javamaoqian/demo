package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: mq
 * @Date: 2020/12/30 16:13
 */
@PropertySource(name = "name",value = {"aaa.properties"})
@Configuration(proxyBeanMethods = false)

public class MyConfig {
    @Value("${name}")
    private String name;

   /* @Bean
    public MyConfig MyConfig(){
       MyConfig m= new  MyConfig();
       m.setName(name);
        return m;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
