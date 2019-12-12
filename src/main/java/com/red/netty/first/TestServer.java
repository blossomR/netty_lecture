package com.red.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//step  1
public class TestServer {
    public static void main(String[] args) throws Exception {
        //接受连接，将连接发送给worker
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker进行真正的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //通过反射的形式创建 NioServerSocketChannel
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerIntitalizer());//这里可以自定义子处理器
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();//绑定端口8899
            channelFuture.channel().closeFuture().sync();
        } finally {
            //netty提供的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}
