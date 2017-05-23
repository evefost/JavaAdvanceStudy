package com.phei.netty.sync.server;

import com.phei.netty.sync.codec.RpcDecoder;
import com.phei.netty.sync.codec.RpcEncoder;
import com.phei.netty.sync.msg.Message;
import com.phei.netty.sync.msg.Respone;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 解码转
        ch.pipeline().addLast(new RpcDecoder(Message.class));
        // 编码器
        ch.pipeline().addLast(new RpcEncoder(Respone.class));
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MyServerHandler());
    }
}
