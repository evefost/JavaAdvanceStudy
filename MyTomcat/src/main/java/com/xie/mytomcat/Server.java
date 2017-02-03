package com.xie.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xieyang on 17/1/25.
 */
public class Server {
    static String rt = System.getProperty("line.separator", "\r\n");

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
            System.out.println();
            InputStream is = null;
            OutputStream os = null;
            try {
                is = socket.getInputStream();
                Request request = new Request(socket.getInputStream());
                os = socket.getOutputStream();
                Response response = new Response(os);
                response.write(request.getUri());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("EX....." + e.getMessage());
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
