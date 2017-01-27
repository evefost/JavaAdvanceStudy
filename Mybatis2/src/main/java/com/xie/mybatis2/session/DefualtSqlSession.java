package com.xie.mybatis2.session;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;
import com.xie.mybatis2.executor.Executor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 默认数据库连接会话
 */
public class DefualtSqlSession implements SqlSession {
    private Configuration config;

    private Executor executor;

    public DefualtSqlSession(Configuration config, Executor executor) {
        this.config = config;
        this.executor = executor;
    }


    public <T> T selectOne(String sql) {
        return null;
    }

    public <T> T selectOne(String sql, Object param) {
        return null;
    }

    public <T> List<T> selectList(String sql, Object param) {

        MappedStatement ms = config.getStatementMap().get(sql);
        try {
            List<T> result = executor.queryList(ms, param);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int insert(String sql) {
        return 0;
    }

    public int insert(String sql, Object param) {
        return 0;
    }

    public int update(String sql) {
        return 0;
    }

    public int update(String sql, Object param) {
        return 0;
    }

    public int delete(String sql) {
        return 0;
    }

    public int delete(String sql, Object param) {
        return 0;
    }

    public void commit() {

    }

    public void rollback() {

    }
}
