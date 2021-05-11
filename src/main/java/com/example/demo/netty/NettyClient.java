package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: mq
 * @Date: 2021/4/1 16:14
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture cf = bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("config:" + ch.config());
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                        System.out.println("active:" + ctx.name() + " " + ctx.channel());
                    }

                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        super.channelInactive(ctx);
                        System.out.println("inactive:" + ctx.name() + " " + ctx.channel());
                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        super.channelRead(ctx, msg);
                        System.out.println("msg:" + msg);
                        System.out.println("read:" + ctx.name() + " " + ctx.channel());
                    }

                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                        super.channelReadComplete(ctx);
                        System.out.println("channelReadComplete:" + ctx.name() + " " + ctx.channel());
                    }

                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                        super.handlerAdded(ctx);
                        System.out.println("handlerAdded:" + ctx.name() + " " + ctx.channel());
                    }
                })
                .connect("192.168.96.180", 5566)
                .sync();
        cf.channel().writeAndFlush(Unpooled.copiedBuffer("hehehehe".getBytes())).sync();
        group.shutdownGracefully();
    }
}
