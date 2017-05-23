package com.phei.netty.sync;

import com.phei.netty.sync.client.ClientSocket;
import com.phei.netty.sync.msg.Message;
import com.phei.netty.sync.msg.Respone;
import com.phei.netty.sync.util.ChannelWriteUtils;
import io.netty.channel.ChannelFuture;


/**
 * Created by fuzhengwei1 on 2016/10/20.
 * 测试过程先启动服务端 org.itstack.demo.netty.test.server.Start
 */
public class ClientStart {

    private static ChannelFuture future;

    public static void main(String[] args) throws InterruptedException {
        ClientSocket client = new ClientSocket();
        Thread thread = new Thread(client);
        thread.start();

        try {
            while (true) {
                //获取future，线程有等待处理时间
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                //构建发送参数
                Message request = new Message();
                request.setSync(true);
                request.setData("查询用户信息");
                Respone respone = ChannelWriteUtils.writeAndFlush(future.channel(), request);
                System.out.println("调用结果：" + respone.getResult());
                //Thread.sleep(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
