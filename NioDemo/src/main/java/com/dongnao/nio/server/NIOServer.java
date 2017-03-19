package com.dongnao.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NIOServer {
    int port = 8080;
    ServerSocketChannel serverSocketChannel;

    Selector selector;


    ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);


    Map<SelectionKey, String> sessionMsg = new HashMap<SelectionKey, String>();

    public NIOServer(int port) throws IOException {

        this.port = port;


        //ServerSocketChannel 服务端channecl
        //SocketChannel 客户端channel
        //打开服务通道
        serverSocketChannel = ServerSocketChannel.open();

        //绑定服务端接入端口
        serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();

        //注册接入事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO Server启动 " + this.port);
    }


    public void listener() throws IOException {

        while (true) {
            //只要反应堆里面没东西了，那么就会阻塞在这个位置（相当于排队）
            int i = selector.selectNow();//阻塞方法,就相当于调用了wakeup，是一个阻塞方法

            if (i == 0) {
                continue;
            }
            System.out.println("反应堆有新的事件触发.......");
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                try {
                    process(key);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("处理处事件异常:" + e.toString());
                    key.channel().close();
                }

                iterator.remove();

            }

        }


    }


    private void process(SelectionKey key) throws IOException {

        if (key.isAcceptable()) {
            System.out.println("process  可接入事件，注册关注读操作事件");
            SocketChannel client = serverSocketChannel.accept();
//			System.out.println(client);
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            System.out.println("process  可读，注册关注读操作事件");
            receiveBuffer.clear();

            SocketChannel client = (SocketChannel) key.channel();
//			System.out.println(client);
            int len = client.read(receiveBuffer);
            if (len > 0) {
                String msg = new String(receiveBuffer.array(), 0, len);
                sessionMsg.put(key, msg);
                System.out.println(System.currentTimeMillis() + " 收到客户端信息:" + msg);
            }
            client.register(selector, SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            System.out.println("process  可写，注册可读作事件");

            if (!sessionMsg.containsKey(key)) {
                return;
            }

            SocketChannel client = (SocketChannel) key.channel();
//			System.out.println(client);

            sendBuffer.clear();

            sendBuffer.put(new String(sessionMsg.get(key) + ",我是服务端").getBytes());

            sendBuffer.flip();//
            client.write(sendBuffer);
            //册注读操作
            client.register(selector, SelectionKey.OP_READ);
            //SelectionKey.OP_ACCEPT//����ˣ�ֻҪ�ͻ������ӣ��ʹ���
            //SelectionKey.OP_CONNECT//�ͻ��ˣ�ֻҪ�����˷���ˣ��ʹ���
            //SelectionKey.OP_READ//ֻҪ�������������ʹ���
            //SelectionKey.OP_WRITE//ֻҪ����д�������ʹ���

        }

    }


    public static void main(String[] args) throws IOException {
        new NIOServer(8080).listener();
    }
}
