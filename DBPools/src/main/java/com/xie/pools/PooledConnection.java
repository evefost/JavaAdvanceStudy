package com.xie.pools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * 自定义的数据库连接对象,
 * 包含真正的物理连接及参数
 * Created by xieyang on 17/1/25.
 */
public class PooledConnection {

    //真正的物理连接
    private Connection connection;

    private boolean isBusy = false;

    public  PooledConnection(Connection connection,boolean isBusy){
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void close(){
        this.isBusy = false;
    }

    public ResultSet queryBySql(String sql){
        Statement sm = null;
        ResultSet rs = null;
        try {
            sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int updateBySql(String sql){
        Statement sm = null;
        int row =-1;
        try {
            sm = connection.createStatement();
            row  = sm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
