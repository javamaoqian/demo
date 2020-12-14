package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author: mq
 * @Date: 2020/7/2 20:29
 */
// @RestController
public class MyController {
   // @Autowired
    private DiscoveryClient discoveryClient;
   // @Autowired
    private Registration registration;

   // @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hellow() {
        String instanceId = registration.getInstanceId();
        List<ServiceInstance> instances = discoveryClient.getInstances(instanceId);
        System.out.println(instanceId);
        System.out.println(instances.size());
        return "Hellow World";
    }

    public static void main(String[] args) {

    }
}
