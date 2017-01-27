package com.xie.mybatis2.resultSetHandler;


import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;

import java.sql.Statement;
import java.util.List;


public class FastResultSetHandler implements ResultSetHandler{
    private Statement stmt;

    private Configuration config;

    private MappedStatement ms;

    public FastResultSetHandler(Statement stmt, Configuration config,
                                MappedStatement ms) {
        this.stmt = stmt;
        this.config = config;
        this.ms = ms;
    }

    public <T> List<T> resultSetHandle(Statement stmt) {
        return null;
    }
}
