package com.example.demo.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: mq
 * @Date: 2021/1/5 11:33
 */
public class TestMySync {
    public static void main(String[] args) throws Exception {
        MySync sync = new MySync();

        sync.acquire(1);
        sync.release(1);
        sync.acquireInterruptibly(1);
        sync.tryAcquireNanos(1, 100);


        sync.acquireSharedInterruptibly(1);
        sync.tryAcquireSharedNanos(1, 100);
        sync.acquireShared(1);
        sync.releaseShared(2);
    }


    static class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            System.out.println(state);
            return true;
        }


    }
}

