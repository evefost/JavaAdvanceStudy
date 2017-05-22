package com.phei.netty.sync.server;

import com.phei.netty.sync.msg.Request;
import com.phei.netty.sync.msg.Respone;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Request msg) throws Exception {
        System.out.println("服务端收到消息:" + msg.getData());
        //反馈
        Respone request = new Respone();
        request.setId(msg.getId());
        request.setResult("我是服务端");
        ctx.writeAndFlush(request);
        //释放
        ReferenceCountUtil.release(msg);
    }

}
