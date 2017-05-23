package com.phei.netty.sync.util;

import com.phei.netty.sync.exception.RemoteException;
import com.phei.netty.sync.future.SyncFuture;
import com.phei.netty.sync.future.WriteFuture;
import com.phei.netty.sync.msg.Message;
import com.phei.netty.sync.msg.Respone;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class ChannelWriteUtils {

    public static long DEFAULT_TIMEOUT = 3000;

    public static Respone writeAndFlush(Channel channel, Message request) throws Exception {
        return writeAndFlush(channel, request, DEFAULT_TIMEOUT);
    }

    public static Respone writeAndFlush(Channel channel, Message request, long timeout) throws Exception {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }
        if (!channel.isActive()) {
            throw new RemoteException("连接未激活或已关闭");
        }
        WriteFuture<Respone> future = new SyncFuture(request.getId());
        return doWriteAndFlush(channel, request, timeout, future);
    }


    private static Respone doWriteAndFlush(Channel channel, Message request, long timeout, WriteFuture<Respone> writeFuture) throws Exception {
        ChannelFuture channelFuture = channel.writeAndFlush(request);
        if (request.isSync()) {
            System.out.println("同步发送");
            Respone response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
            if (channelFuture.cause() != null) {
                if (channelFuture.cause() instanceof ClosedChannelException) {
                    throw new RemoteException("连接已关闭");
                }
                throw new RemoteException("远端连接异常");
            }
            if (response == null) {
                throw new TimeoutException("请求超时");
            }
            return response;
        } else {
            System.out.println("异步发送");
            return new Respone();
        }

    }

}
