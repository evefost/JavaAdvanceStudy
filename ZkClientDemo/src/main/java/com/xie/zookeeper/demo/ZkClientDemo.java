package com.xie.zookeeper.demo;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by xieyang on 17/2/12.
 */
public class ZkClientDemo {

    private static String CONNECT_STRING = "172.16.165.128:2181,172.16.165.128:2182,172.16.165.128:2183";

    private static int SESSION_TIMEOUT = 3000;

    public static void main(String[] args) {

        // ZkClient zkClient = new ZkClient(CONNECT_STRING, SESSION_TIMEOUT, SESSION_TIMEOUT, new SerializableSerializer());
        ZkClient zkClient = new ZkClient(CONNECT_STRING, SESSION_TIMEOUT, SESSION_TIMEOUT, new MyZkSerializer());
        try {

            //创建临时节点
            //zkClient.createEphemeral("/node_10","abc");
            //持久节点
            if (!zkClient.exists("/node_12")) {
                zkClient.createPersistent("/node_12", "bbb11");
            }

            //持久有序
            //zkClient.createPersistentSequential()

            System.out.println("data:" + zkClient.readData("/node_12").toString());

        } finally {
            zkClient.close();
        }
    }


}
