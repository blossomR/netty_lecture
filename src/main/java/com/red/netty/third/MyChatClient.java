package com.red.netty.third;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 自定义客户端
 */
public class MyChatClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            //对于客户端来说，只用handler，服务端用childHandler和handler都可以
            //服务端：childhander-> worker  /  handler-》 bossHandler
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", 9998).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //不断从控制台获取输入信息，放到channel中，发送到服务端
            for(;;){
                channel.writeAndFlush(br.readLine()+"\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
