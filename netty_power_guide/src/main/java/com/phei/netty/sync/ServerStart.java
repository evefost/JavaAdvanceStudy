package com.phei.netty.sync;


import com.phei.netty.sync.server.ServerSocket;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public class ServerStart {

    public static void main(String[] args) {
        System.out.println("启动服务端开始");
        Thread t = new Thread(new ServerSocket());
        t.start();
        System.out.println("启动服务端完成");
    }

}
