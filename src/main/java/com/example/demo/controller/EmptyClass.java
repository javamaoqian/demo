package com.example.demo.controller;

import java.lang.reflect.AnnotatedType;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: mq
 * @Date: 2020/7/6 18:11
 */
public class EmptyClass {
    public static void main(String[] args) {
        Class clazz = NoneAnnotationClass.class;
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = clazz.getAnnotatedSuperclass();
        MyAnnotation annotation = (MyAnnotation) clazz.getAnnotation(MyAnnotation.class);

        String canonicalName = clazz.getCanonicalName();
        Class[] classes = clazz.getClasses();
        String typeName = clazz.getTypeName();
        System.out.println(canonicalName + " " + typeName);
        System.out.println(System.getProperty("java.library.path"));
    }
}
