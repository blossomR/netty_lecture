package com.red.netty.second;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 自定义客户端
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            //对于客户端来说，只用handler，服务端用childHandler和handler都可以
            //服务端：childhander-> worker  /  handler-》 bossHandler
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();

        }

    }
}
