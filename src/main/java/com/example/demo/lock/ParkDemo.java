package com.example.demo.lock;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: mq
 * @Date: 2020/8/13 16:37
 */
public class ParkDemo {
    private static Object lock = new Object();
    private static ReentrantLock rtl = new ReentrantLock();
    private static Condition FULL = rtl.newCondition();
    private static Condition EMPTY = rtl.newCondition();
    private static Res res = new Res(10);

    static class Res {
        Integer res;

        public void addRes() {
            res = res + 1;
        }

        public void decrRes() {
            res = res - 1;
        }

        public Res(Integer res) {
            this.res = res;
        }

        public Integer getRes() {
            return res;
        }

        public void setRes(Integer res) {
            this.res = res;
        }
    }

    static class MyThread1 implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备进入lock");
            LockSupport.park();
            System.out.println(name + "结束park");
        }
    }

    static class MyThread2 implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备进入lock");
            LockSupport.parkNanos(10000000000L);
            System.out.println(name + "结束park");
        }
    }

    static class MyThread3 implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 进入同步锁");
                try {
                    lock.wait(10000);
                    int i = 0;
                    while (i < 100) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wait结束");
            }
        }
    }

    static class MyThread4 implements Runnable {
        @Override
        public void run() {
            boolean locked = false;
            try {
                locked = rtl.tryLock(10, TimeUnit.SECONDS);
                if (locked) {
                    int i = 0;
                    while (i < 10) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (locked) {
                    rtl.unlock();
                }
            }
        }
    }

    static class Producessor implements Runnable {
        private Res res;

        public Producessor(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            try {
                rtl.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
                while (true) {
                    while (res.getRes() >= 10) {
                        System.out.println(Thread.currentThread().getName() + "释放锁");
                        EMPTY.signal();
                        FULL.await();
                        System.out.println(Thread.currentThread().getName() + "获得锁");
                    }
                    res.addRes();
                    System.out.println(Thread.currentThread().getName() + "增加1个资源,当前资源有" + res.getRes());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rtl.unlock();
            }
        }
    }

    static class Consummer implements Runnable {
        private Res res;

        public Consummer(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            try {
                rtl.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
                while (true) {
                    while (res.getRes() < 3) {
                        System.out.println(Thread.currentThread().getName() + "释放锁");
                        FULL.signal();
                        EMPTY.await();
                        System.out.println(Thread.currentThread().getName() + "获得锁");
                    }
                    res.decrRes();
                    System.out.println(Thread.currentThread().getName() + "消耗1个资源,当前资源有" + res.getRes());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rtl.unlock();
            }
        }
    }

    public static void main(String[] args) {

       /* for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Producessor(res));
            t.setName("producessor" + i);
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Consummer(res));
            t.setName("consummer" + i);
            t.start();
        }*/

        Integer[] a = new Integer[]{1, 3, 4, 2, 6};
        Integer[] b = a;
        b[0] = 7;
        System.out.println(Arrays.toString(a));
    }


}
