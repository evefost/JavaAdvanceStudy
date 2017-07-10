package com.xie.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by xieyang on 17/2/14.
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        String uri = fullHttpRequest.uri();
        System.out.println(" url:" + uri);
        ctx.writeAndFlush("Hellow world");
        ctx.close();
    }
}
