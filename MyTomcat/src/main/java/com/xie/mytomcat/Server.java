package com.xie.mytomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by xieyang on 17/1/25.
 */
public class Server {
    static String rt =  System.getProperty("line.separator", "\r\n");

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();
                new RequestThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class RequestThread extends Thread {

        private Socket socket;

        public RequestThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("处理新的socket请求 thread========:" + Thread.currentThread().getName());
            try {
                InputStream is = socket.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = bf.readLine()) != null && !"".equals(line)) {
                    System.out.println(line);
                }

                System.out.println();
                String content = "欢迎光临....";
                int contentLength = content.getBytes(Charset.defaultCharset()).length;
                StringBuffer sb = new StringBuffer();
                sb.append("HTTP/1.1 200 OK" + rt)
                        .append("Server: Apache-Coyote/1.1" + rt)
                        .append("Content-Length: " + contentLength + rt)
                        .append("Date: Fri, 27 Jan 2017 08:24:49 GMT" + rt)
                        .append("Content-Type:text/plain;charset:utf-8" + rt)
                        .append(rt)
                        .append(content);

                System.out.println();
                System.out.println(sb.toString());
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.write(sb.toString());
                pw.flush();
                System.out.println("处理完.....");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("EX....." + e.getMessage());
            }
        }
    }
}
