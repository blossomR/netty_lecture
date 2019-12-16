package com.red.netty.fourth;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //当一个channel没有进行读/写/读写的操作的时候将会触发IdleStateHandler事件
        //空闲检测
        pipeline.addLast(new IdleStateHandler(3,5,2, TimeUnit.SECONDS));
        //自定義處理器
        pipeline.addLast(new MyServerHandler());
    }

}
