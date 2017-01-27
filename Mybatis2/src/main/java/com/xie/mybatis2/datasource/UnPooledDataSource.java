package com.xie.mybatis2.datasource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * 无线程池数据源
 */
public class UnPooledDataSource implements DataSource {

    private static String jdbcDriver = "";
    private static String jdbccurl = "";
    private static String userName = "";
    private static String password = "";

    public UnPooledDataSource(String jdbcDriver, String jdbccurl, String userName, String password) {

        this.jdbcDriver = jdbcDriver;
        this.jdbccurl = jdbccurl;
        this.userName = userName;
        this.password = password;

    }


    public Connection getConnection() {
        try {
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(driver);
           return DriverManager.getConnection(jdbccurl,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJdbcDriver() {
        return jdbcDriver;
    }

    public static void setJdbcDriver(String jdbcDriver) {
        UnPooledDataSource.jdbcDriver = jdbcDriver;
    }

    public static String getJdbccurl() {
        return jdbccurl;
    }

    public static void setJdbccurl(String jdbccurl) {
        UnPooledDataSource.jdbccurl = jdbccurl;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UnPooledDataSource.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UnPooledDataSource.password = password;
    }
}
