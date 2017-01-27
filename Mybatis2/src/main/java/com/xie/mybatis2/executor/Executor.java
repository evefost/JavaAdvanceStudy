package com.xie.mybatis2.executor;

import com.xie.mybatis2.configTemplate.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器,为什么要加一个执行器?
 */
public interface Executor {

    <T> List<T> queryList(MappedStatement ms, Object param) throws SQLException;

    int update(MappedStatement ms, Object param) throws SQLException;

    void commit();

    void rollback();
}
