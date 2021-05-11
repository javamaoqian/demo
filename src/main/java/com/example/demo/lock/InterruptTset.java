package com.example.demo.lock;/**
 * @Author: mq
 * @Date: 2021/3/12 17:30
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class InterruptTset {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("park end");
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.interrupt();
            System.out.println("interrupt T1");
        });
        t2.start();
    }

}
