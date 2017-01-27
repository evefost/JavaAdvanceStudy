/** 
 * @Project     DN-jack-mybatis 
 * @File        SimpleExecutor.java 
 * @Package     com.dongnao.jack.executor 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:28:26 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.MappedStatement;
import com.dongnao.jack.sqlNode.BoundSql;
import com.dongnao.jack.statementHandler.SimpleStatementHandler;
import com.dongnao.jack.statementHandler.StatementHandler;
import com.dongnao.jack.transation.Transation;

/** 
 * @Description TODO 
 * @ClassName   SimpleExecutor 
 * @Date        2016年10月27日 下午10:28:26 
 * @Author      dongnao.jack 
 */

public class SimpleExecutor implements Executor {
    
    private Configuration config;
    
    private Transation transation;
    
    public SimpleExecutor(Configuration config, Transation transation) {
        this.config = config;
        this.transation = transation;
    }
    
    public Configuration getConfig() {
        return config;
    }
    
    public void setConfig(Configuration config) {
        this.config = config;
    }
    
    public Transation getTransation() {
        return transation;
    }
    
    public void setTransation(Transation transation) {
        this.transation = transation;
    }
    
    /* 
     * @see com.dongnao.jack.executor.Executor#queryList(com.dongnao.jack.configTemplate.MappedStatement, java.lang.Object)
     */
    @Override
    public <T> List<T> queryList(MappedStatement ms, Object param)
            throws SQLException {
        
        //根据参数解析我们的SQL，sql的来源MappedStatement- sqlSource
        BoundSql boundSql = ms.getBoundSql(param);
        
        StatementHandler handler = new SimpleStatementHandler(boundSql, config,
                ms, transation.getConnection());
        
        Statement stmt = handler.createStatement(transation.getConnection());
        
        List<T> result = handler.query(stmt);
        
        return result;
    }
    
    /* 
     * @see com.dongnao.jack.executor.Executor#update(com.dongnao.jack.configTemplate.MappedStatement, java.lang.Object)
     */
    @Override
    public int update(MappedStatement ms, Object param) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.executor.Executor#commit()
     */
    @Override
    public void commit() {
        // TODO Auto-generated method stub
        
    }
    
    /* 
     * @see com.dongnao.jack.executor.Executor#rollback()
     */
    @Override
    public void rollback() {
        // TODO Auto-generated method stub
        
    }
    
}
