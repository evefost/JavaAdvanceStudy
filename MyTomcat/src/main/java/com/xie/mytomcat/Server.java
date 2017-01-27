package com.xie.mytomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xieyang on 17/1/25.
 */
public class Server {

    public static  void main(String[] args) {
        ServerSocket ss =null;
        try {
            ss = new ServerSocket(9999);
            while (true){
                Socket socket = ss.accept();
                new RequestThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  static class RequestThread extends  Thread{

        private Socket socket;
        public  RequestThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("处理新的socket请求 thread========:"+Thread.currentThread().getName());
            try {
                InputStream is = socket.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                String line = null;
               // is.close();
//                while ((line = bf.readLine())!= null){
//                    System.out.println(line);
//                }
                PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
                pw.write("欢迎光临...");
                //pw.close();
                System.out.println("处理完.....");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("EX....."+e.getMessage());
            }
        }
    }
}
