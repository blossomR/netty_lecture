package com.red.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        //阻塞
        socketChannel.configureBlocking(true);
        String fileName = "E:\\copy.png";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();

        //transferTo：将文件中的字节内容(从position到count)写到给定的channel中，即：socketChannel
        //transferTof 方法 用到 【零拷贝】
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数     " + transferCount + "，耗时     " + (System.currentTimeMillis() - startTime)+" ms");

        fileChannel.close();


    }
}
