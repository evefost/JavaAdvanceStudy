package com.xie.pools;

import java.sql.ResultSet;

/**
 * Created by xieyang on 17/1/25.
 */
public class MyPoolTest {
    private static IMyPool myPool = PoolManager.getInstance();

    public static void main(String[] args) {
        for (int i=0;i<1;i++){
            new Thread(new Runnable() {
                public void run() {
                    selectData();
                }
            }).start();
        }

    }

    public synchronized static void selectData() {
        PooledConnection connection = myPool.getConnection();
        ResultSet rs = connection.queryBySql("select * from tb1");
        System.out.println("线程名称:" + Thread.currentThread().getName());
        try {
            while (rs.next()) {
                System.out.println("id:" + rs.getString("id") + "===name" + rs.getString("name"));

            }
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
