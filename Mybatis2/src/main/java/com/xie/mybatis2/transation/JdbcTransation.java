package com.xie.mybatis2.transation;

import com.xie.mybatis2.datasource.DataSource;

import java.sql.Connection;

/**
 * Created by xieyang on 17/1/26.
 */
public class JdbcTransation implements Transation {

    private DataSource dataSource;

    public JdbcTransation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return dataSource.getConnection();
    }

    public void commit() {

    }

    public void rollback() {

    }

    public void close() {

    }
}
