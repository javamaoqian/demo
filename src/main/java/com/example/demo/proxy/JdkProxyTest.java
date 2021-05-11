package com.example.demo.proxy;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.controller.MyAnnotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: mq
 * @Date: 2021/4/16 09:09
 */
public class JdkProxyTest implements Fly {


    @Override
    public void fly() {
        System.out.println("fly>>>>>>>>");
    }

    @Override
    @MyAnnotation
    public void run() {
        System.out.println("run>>>>>>>>");
    }

    @Override
    public void run(String s) {
        System.out.println(s+"s:run>>>>>>>>");
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Fly o = (Fly) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(), new Class[]{Fly.class}, new MyInvocationHandler(new JdkProxyTest()));
        System.out.println(o.getClass().getName());
        o.fly();
        o.run();
        o.run("s");
    }


    static class MyInvocationHandler implements InvocationHandler {
        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Method m = target.getClass().getMethod(method.getName());
            if(m==null){
                return null;
            }
            if (m.getAnnotation(MyAnnotation.class) != null) {
                System.out.println("前置操作》》》》》》》》》");
                Object invoke = method.invoke(target, args);
                System.out.println("后置操作》》》》》》》》》");
                return invoke;
            }
            return method.invoke(target, args);
        }
    }

}
