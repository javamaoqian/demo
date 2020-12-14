package com.example.demo.controller;

import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: mq
 * @Date: 2020/11/27 17:36
 */
public class AnnotationMetadataTest {
    public static void main(String[] args) {
        AnnotationMetadata introspect = AnnotationMetadata.introspect(NoneAnnotationClass.class);
        System.out.println(introspect.getAnnotationTypes());
        introspect.getMetaAnnotationTypes("com.example.demo.controller.MyAnnotation");
    }
}
