package com.xie.pools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by xieyang on 17/1/25.
 */
public class MyPoolImpl implements IMyPool {
    //准备好数据库连接的参数
    private static String jdbcDriver = "";
    private static String jdbccurl = "";
    private static String userName = "";
    private static String password = "";
    private static int initCount;
    private static int stepSize;
    private static int poolMaxSize;

    //封装一个连接对象池的数据结构
    private Vector<PooledConnection> poolConnections = new Vector<PooledConnection>();

    public MyPoolImpl() {
        init();
    }


    {
        System.out.println("bbbbb");
    }

    static {
        System.out.println("aaaaaa");
    }

    //初始化方法
    public void init() {
        InputStream in = IMyPool.class.getClassLoader().getResourceAsStream("jdbcPool.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDriver = pro.getProperty("jdbcDriver");
        jdbccurl = pro.getProperty("jdbccurl");
        userName = pro.getProperty("userName");
        password = pro.getProperty("password");
        initCount = Integer.parseInt(pro.getProperty("initCount"));
        stepSize = Integer.parseInt(pro.getProperty("stepSize"));
        poolMaxSize = Integer.parseInt(pro.getProperty("poolMaxSize"));
        //创建数据库连接
        try {
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
            //将驱动注册
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将创建物理连接
        createConnections(initCount);
    }

    /**
     * 拿取连接对象来消费
     *
     * @return
     */
    public PooledConnection getConnection() {
        if (poolConnections.size() <= 0) {
            System.out.println("连接池中没有连接对象!");
            throw new RuntimeException("获取连接对象失败,连接池中没有连接对象!");

        }
        //获取空闲的连接对象
        PooledConnection readConnection = getReadConnection();
        while (readConnection == null) {
            createConnections(stepSize);
            readConnection = getReadConnection();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return readConnection;
    }

    private PooledConnection getReadConnection() {
        for (PooledConnection conn : poolConnections) {
            if (!conn.isBusy()) {
                Connection connection = conn.getConnection();
                //判断是否有效
                try {
                    if (!connection.isValid(2000)) {
                        //代码连接对象不能使用
                        Connection avildConn = DriverManager.getConnection(jdbccurl, userName, password);
                        conn.setConnection(avildConn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn.setBusy(true);
                return conn;
            }
        }
        return null;
    }


    /**
     * 创建连接对象的方法
     */
    public void createConnections(int count) {
        for (int i = 0; i < count; i++) {
            if (poolMaxSize > 0 && poolConnections.size() + count > poolMaxSize) {
                System.out.println("创建数据库连接池失败,超过连接池上限值!");
                throw new RuntimeException("创建数据库连接池失败,超过连接池上限值!");
            }
            try {
                Connection connection = DriverManager.getConnection(jdbccurl, userName, password);
                //顺便设备一下它的标注
                PooledConnection poolConnection = new PooledConnection(connection, false);
                poolConnections.add(poolConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
