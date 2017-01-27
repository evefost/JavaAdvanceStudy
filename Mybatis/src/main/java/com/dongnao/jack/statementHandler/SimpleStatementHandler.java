/** 
 * @Project     DN-jack-mybatis 
 * @File        SimpleStatementHandler.java 
 * @Package     com.dongnao.jack.statementHandler 
 * @Version     V1.0 
 * @Date        2016年10月31日 下午8:52:19 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.statementHandler;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.MappedStatement;
import com.dongnao.jack.connectionHandler.ConnectionHandler;
import com.dongnao.jack.resutlSetHandler.FastResultSetHandler;
import com.dongnao.jack.resutlSetHandler.ResultSetHandler;
import com.dongnao.jack.sqlNode.BoundSql;

/** 
 * @Description TODO 
 * @ClassName   SimpleStatementHandler 
 * @Date        2016年10月31日 下午8:52:19 
 * @Author      dongnao.jack 
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
    
    /* 
     * @see com.dongnao.jack.statementHandler.StatementHandler#query(java.sql.Statement)
     */
    @Override
    public <T> List<T> query(Statement stmt) throws SQLException {
        
        stmt.execute(boundSql.getSql());
        
        ResultSetHandler resultSetHandler = new FastResultSetHandler(stmt,
                config, ms);
        
        List<T> result = resultSetHandler.resultSetHandle(stmt);
        return result;
    }
    
    /* 
     * @see com.dongnao.jack.statementHandler.StatementHandler#update(java.sql.Statement)
     */
    @Override
    public int update(Statement stmt) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.statementHandler.StatementHandler#createStatement(java.sql.Connection)
     */
    @Override
    public Statement createStatement(Connection connection) throws SQLException {
        
        connection = (Connection)Proxy.newProxyInstance(Connection.class.getClassLoader(),
                new Class<?>[] {Connection.class},
                new ConnectionHandler(connection, boundSql));
        
        return connection.createStatement();
    }
}
