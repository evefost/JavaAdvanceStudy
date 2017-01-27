/** 
 * @Project     DN-jack-mybatis 
 * @File        DefaultSqlSession.java 
 * @Package     com.dongnao.jack.session 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:14:03 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.MappedStatement;
import com.dongnao.jack.executor.Executor;

/** 
 * @Description TODO 
 * @ClassName   DefaultSqlSession 
 * @Date        2016年10月27日 下午10:14:03 
 * @Author      dongnao.jack 
 */

public class DefaultSqlSession implements SqlSession {
    
    private Configuration config;
    
    private Executor executor;
    
    public DefaultSqlSession(Configuration config, Executor executor) {
        this.config = config;
        this.executor = executor;
    }
    
    @Override
    public <T> List<T> selectList(String sql, Object param) {
        
        MappedStatement ms = config.getMappedStatements().get(sql);
        
        if (ms == null) {
            throw new RuntimeException();
        }
        
        try {
            List<T> result = executor.queryList(ms, param);
            return result;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#selectOne(java.lang.String)
     */
    @Override
    public <T> T selectOne(String sql) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#selectOne(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> T selectOne(String sql, Object param) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#insert(java.lang.String)
     */
    @Override
    public int insert(String sql) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#insert(java.lang.String, java.lang.Object)
     */
    @Override
    public int insert(String sql, Object param) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#update(java.lang.String)
     */
    @Override
    public int update(String sql) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#update(java.lang.String, java.lang.Object)
     */
    @Override
    public int update(String sql, Object param) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#delete(java.lang.String)
     */
    @Override
    public int delete(String sql) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#delete(java.lang.String, java.lang.Object)
     */
    @Override
    public int delete(String sql, Object param) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#commit()
     */
    @Override
    public void commit() {
        // TODO Auto-generated method stub
        
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#rollback()
     */
    @Override
    public void rollback() {
        // TODO Auto-generated method stub
        
    }
    
    /* 
     * @see com.dongnao.jack.session.SqlSession#getConnection()
     */
    @Override
    public Connection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
