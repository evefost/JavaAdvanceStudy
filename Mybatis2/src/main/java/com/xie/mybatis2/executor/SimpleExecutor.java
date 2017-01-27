package com.xie.mybatis2.executor;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;
import com.xie.mybatis2.sqlNode.BoundSql;
import com.xie.mybatis2.statementHandler.SimpleStatementHandler;
import com.xie.mybatis2.statementHandler.StatementHandler;
import com.xie.mybatis2.transation.Transation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    private Transation transation;

    public SimpleExecutor(Configuration configuration, Transation transation) {
        this.configuration = configuration;
        this.transation = transation;
    }

    public <T> List<T> queryList(MappedStatement ms, Object param) throws SQLException {

        //根据参数解析我们的SQL，sql的来源MappedStatement- sqlSource
        BoundSql boundSql = ms.getBoundSql(param);
        StatementHandler handler = new SimpleStatementHandler(boundSql,configuration,
                ms, transation.getConnection());

        Statement stmt = handler.createStatement(transation.getConnection());

        List<T> result = handler.query(stmt);

        return result;
    }

    public int update(MappedStatement ms, Object param) throws SQLException {

        return 0;
    }

    public void commit() {

    }

    public void rollback() {

    }
}
