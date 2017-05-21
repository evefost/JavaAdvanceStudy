package com.packageprope;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.IOException;

/**
 * 聊天服务端
 *
 * @author Tom
 */
public class FixLengthPackageServer {

    private int port = 8080;//服务器启动的监听端口

    public void start() {

        //主从模式、创建主线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建子线程，工作线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //创建Netty Socket Server
            ServerBootstrap b = new ServerBootstrap();
            //默认分配1024个工作线程
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //获取 工作流，流水线,pipeline
                            ChannelPipeline pipeline = ch.pipeline();


                            //粘包拆包
//                	pipeline.addLast(new LineBasedFrameDecoder(1024));

                            //======== 对自定义IMP协议的支持  ==========
                            pipeline.addLast(new FixedLengthFrameDecoder(10));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new EchoServerHanlder());

                        }
                    });
            //采用同步的方式监听客户端连接
            //NIO同步非阻塞
            ChannelFuture f = b.bind(this.port).sync();
            System.out.println("服务已启动,监听端口" + this.port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws IOException {
        new FixLengthPackageServer().start();
    }
}
