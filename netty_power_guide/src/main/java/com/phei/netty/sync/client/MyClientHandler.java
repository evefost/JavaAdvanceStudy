package com.phei.netty.sync.client;

import com.phei.netty.sync.future.SyncFuture;
import com.phei.netty.sync.msg.Message;
import com.phei.netty.sync.msg.Respone;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Message> {

    volatile long count;
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println("客户端收到消息:" + msg.getData());
        count++;
        System.out.println(count);
        Respone respone = new Respone();
        respone.setId(msg.getId());
        respone.setResult(msg.getData());
        SyncFuture.received(ctx.channel(), respone);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println(cause.getMessage());
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        System.out.println("连接已关闭");
    }
}
