package com.phei.netty.sync.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class ClientSocket implements Runnable {

    private ChannelFuture future;

    @Override
    public void run() {

        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(workGroup);
            b.channel(NioSocketChannel.class);
            b.handler(new ChildChannelHandler());
            b.option(ChannelOption.SO_KEEPALIVE, true);
            //端口可配
            future = b.connect("127.0.0.1", 7398).sync();
            if (future.isSuccess()) {
                setFuture(future);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("connect is close");
        }
    }

    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }
}
