package com.phei.netty.sync.client;

import com.phei.netty.sync.codec.RpcDecoder;
import com.phei.netty.sync.codec.RpcEncoder;
import com.phei.netty.sync.msg.Request;
import com.phei.netty.sync.msg.Respone;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(20, 10, 0));
        ch.pipeline().addLast(new RpcDecoder(Respone.class));
        ch.pipeline().addLast(new RpcEncoder(Request.class));
        ch.pipeline().addLast(new MyClientHandler());
    }

}
