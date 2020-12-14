package com.mao.nio;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: mq
 * @Date: 2020/12/9 16:21
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress("localhost", 8001));
        ssc.configureBlocking(true);
        while (true) {
            SocketChannel accept = ssc.accept();
            System.out.println("接受到socket"+accept.getLocalAddress());
        }
    }
}
