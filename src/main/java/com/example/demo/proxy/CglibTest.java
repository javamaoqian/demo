package com.example.demo.proxy;

import com.example.demo.controller.MyAnnotation;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: mq
 * @Date: 2021/4/16 10:56
 */
public class CglibTest implements MethodInterceptor {
    private Object target;

    public CglibTest() {
    }

    public CglibTest(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @MyAnnotation
    public void run() {
        System.out.println("run>>>>>>>>>>");
    }

    public void fly() {
        System.out.println("fly>>>>>>>>>>");
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before>>>>>>>>>>>>>>>>>>>>>");
        return proxy.invoke(target, args);
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\demo\\demo\\com\\cglib");
        Enhancer en = new Enhancer();
        en.setSuperclass(CglibTest.class);
        en.setCallbacks(new Callback[]{new CglibTest(new CglibTest()), NoOp.INSTANCE});
        en.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getAnnotation(MyAnnotation.class) != null) {
                    return 0;
                }
                return 1;
            }
        });
        en.setNamingPolicy(new NamingPolicy() {
            private AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public String getClassName(String prefix, String source, Object key, Predicate names) {
                return prefix + atomicInteger.getAndIncrement();
            }
        });
        CglibTest o = (CglibTest) en.create();
        o.run();
        o.fly();
    }
}
