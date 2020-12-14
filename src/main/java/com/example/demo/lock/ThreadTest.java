package com.example.demo.lock;

/**
 * @Author: mq
 * @Date: 2020/12/1 15:48
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception {
        System.out.println(5>>5);
    }

    public void test1() throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1线程执行完成");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2线程执行完成");
            }
        });

        t1.start();
        t1.join();
        t2.start();
        System.out.println(t1.getState());
        System.out.println(t1.isAlive());
        t1.join();
        System.out.println("main over");
    }
}
