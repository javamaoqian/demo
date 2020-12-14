package com.mao.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Author: mq
 * @Date: 2020/12/9 14:27
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost", 8001));
        sc.configureBlocking(false);
        SelectionKey key = sc.register(Selector.open(), SelectionKey.OP_CONNECT);
        System.out.println(key.isConnectable());
        System.out.println(key.isAcceptable());
        System.out.println(key.isReadable());
        System.out.println(key.isWritable());
    }
}
