package com.example.demo.lock;

import org.springframework.boot.ansi.AnsiOutput;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: mq
 * @Date: 2020/12/23 15:41
 */
public class JUCTest {
    static CountDownLatch cdl = new CountDownLatch(1);
    private int count;

    public JUCTest(int count) {
        this.count = count;
    }

    public JUCTest() {
    }

    public static void main(String[] args) throws Exception {
        t5();

    }

    public static void t1() throws Exception {

        new Thread(() -> {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        }).start();
    }

    /** park 状态会被interrupted */
    public static void t2() throws Exception {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println(Thread.currentThread().isInterrupted());
        });
        t.start();
        TimeUnit.SECONDS.sleep(10);

        //  t.interrupt();
    }

    public static void t3() throws Exception {
        JUCTest j = new JUCTest();
        FutureTask t = new FutureTask(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(j.count);
                    j.setCount(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, j);
        t.run();
        JUCTest o = (JUCTest) t.get(15, TimeUnit.SECONDS);
        System.out.println(o.getCount());
    }


    public static void t4() {
        FutureTask t = new FutureTask(() -> {
            //TimeUnit.SECONDS.sleep(3);
            return new JUCTest(1);
        });
        new Thread(() -> {
            try {
                JUCTest test = (JUCTest) t.get();
                System.out.println(test.getCount());
                test.setCount(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                JUCTest test = (JUCTest) t.get();
                System.out.println(test.getCount());
                test.setCount(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
        t.run();


    }

    public static void t5() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                tpe.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + "：over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        }

        System.out.println(tpe.getActiveCount());
    }

    public static CountDownLatch getCdl() {
        return cdl;
    }

    public static void setCdl(CountDownLatch cdl) {
        JUCTest.cdl = cdl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
