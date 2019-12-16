package com.red.netty.five;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        //对http消息进行聚合的处理器
        pipeline.addLast(new HttpObjectAggregator(8192));

        // 负责处理websocket握手以及处理控制帧（关闭，乒乓，乒乓）。文字和二进制
        // 数据帧被传递到管道中的下一个处理程序（由您实现）进行处理
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast();


    }

}
