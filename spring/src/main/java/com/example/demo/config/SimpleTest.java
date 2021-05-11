package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: mq
 * @Date: 2021/4/15 14:53
 */

@Controller
@RequestMapping("/test")
public class SimpleTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Value("${myConfig.name}")
    private String name;
    @RequestMapping("/{a}/{b}")
    @ResponseBody
    public String testAop(@PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        int x = a / b;
        System.out.println("run");
        Environment environment = applicationContext.getEnvironment();
        MyConfig m=  (MyConfig)applicationContext.getBean(MyConfig.class);
        System.out.println(m.getName());
        System.out.println(name);
        return Integer.toString(x);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
