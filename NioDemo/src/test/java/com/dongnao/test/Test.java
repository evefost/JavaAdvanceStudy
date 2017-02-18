package com.dongnao.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

public class Test {

    public static void main(String[] args) {

        final byte[] CONTENT = new byte[1024];
        int loop = 100000000; //假设有300万个用户连接到服务器，每个用户分配一个缓冲区
        //记录分配内存空开始之前记录一下时间
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer = null;
        for (int i = 0; i < loop; i++) {
            //分配直接缓冲区
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            //将数据写入到缓冲区里面
            poolBuffer.writeBytes(CONTENT);
            //释放缓冲区
            poolBuffer.release();
        }
        //分配完之后，也记录一下时间
        long endTime = System.currentTimeMillis();
        System.out.println("内存池分配缓冲区耗时" + (endTime - startTime) + "ms.");


        long startTime2 = System.currentTimeMillis();
        ByteBuf buffer = null;
        for (int i = 0; i < loop; i++) {
            buffer = Unpooled.directBuffer(1024);
            buffer.writeBytes(CONTENT);
            //直接缓冲区自己的释放方法
            buffer.release();
        }
        endTime = System.currentTimeMillis();
        System.out.println("非内存池分配缓冲区耗时" + (endTime - startTime2) + "ms.");

    }
}
