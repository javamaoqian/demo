package com.example.demo.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: mq
 * @Date: 2021/1/5 11:33
 */
public class MySync extends AbstractQueuedSynchronizer {

    @Override
    protected int tryAcquireShared(int arg) {
        return -1;
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return tryReleaseShared(arg);
    }
}
