package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Constant;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @Author: mq
 * @Date: 2021/4/1 16:53
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        simpleRead(ctx, msg);

    }


    /**
     * 最简单的处理
     * @param ctx
     * @param msg
     * @throws UnsupportedEncodingException
     */
    public void simpleRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf bb = (ByteBuf)msg;
        // 创建一个和buf同等长度的字节数组
        byte[] reqByte = new byte[bb.readableBytes()];
        // 将buf中的数据读取到数组中
        bb.readBytes(reqByte);
        String reqStr = new String(reqByte, Charset.defaultCharset());
        System.out.println("server 接收到客户端的请求： " + reqStr);
        String respStr = new StringBuilder("来自服务器的响应").append(reqStr).append("$_").toString();
        // 返回给客户端响应                                                                                                                                                       和客户端链接中断即短连接，当信息返回给客户端后中断
        ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("服务端读取数据完毕");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("server 读取数据出现异常");
        ctx.close();
    }

}
