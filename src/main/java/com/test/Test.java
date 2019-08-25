package com.test;

import com.pojo.UserAccount;
import com.service.UserAccountService;
import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserAccountService bean = context.getBean(UserAccountService.class);
        bean.insert(3,"张四",10000);
       // UserAccount user = bean.getUser(1);
        //System.out.println(user);
    }
}
