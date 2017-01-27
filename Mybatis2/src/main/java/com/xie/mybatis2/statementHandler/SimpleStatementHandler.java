/**
 * @Project DN-jack-mybatis
 * @File SimpleStatementHandler.java
 * @Package com.dongnao.jack.statementHandler
 * @Version V1.0
 * @Date 2016年10月31日 下午8:52:19
 * @Author dongnao.jack
 */

package com.xie.mybatis2.statementHandler;



import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;
import com.xie.mybatis2.connectionHandler.ConnectionHandler;
import com.xie.mybatis2.resultSetHandler.FastResultSetHandler;
import com.xie.mybatis2.resultSetHandler.ResultSetHandler;
import com.xie.mybatis2.sqlNode.BoundSql;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 */
public class SimpleStatementHandler implements StatementHandler {

    private BoundSql boundSql;

    private Configuration config;

    private MappedStatement ms;

    private Connection connection;

    public SimpleStatementHandler(BoundSql boundSql, Configuration config,
                                  MappedStatement ms, Connection connection) {
        this.boundSql = boundSql;
        this.config = config;
        this.ms = ms;
        this.connection = connection;
    }


    public <T> List<T> query(Statement stmt) throws SQLException {

        stmt.execute(boundSql.getSql());

        ResultSetHandler resultSetHandler = new FastResultSetHandler(stmt,
                config, ms);

        List<T> result = resultSetHandler.resultSetHandle(stmt);
        return result;
    }


    public int update(Statement stmt) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }


    public Statement createStatement(Connection connection) throws SQLException {

        connection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),
                new Class<?>[]{Connection.class},
                new ConnectionHandler(connection, boundSql));

        return connection.createStatement();
    }
}
