/** 
 * @Project     DN-jack-mybatis 
 * @File        JdbcTransation.java 
 * @Package     com.dongnao.jack.transation 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:23:01 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.transation;

import java.sql.Connection;

import com.dongnao.jack.datasource.DataSource;

/** 
 * @Description TODO 
 * @ClassName   JdbcTransation 
 * @Date        2016年10月27日 下午10:23:01 
 * @Author      dongnao.jack 
 */

public class JdbcTransation implements Transation {
    
    private DataSource dataSource;
    
    public JdbcTransation(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Connection getConnection() {
        // TODO Auto-generated method stub
        return dataSource.getConnection();
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /* 
     * @see com.dongnao.jack.transation.Transation#commit()
     */
    @Override
    public void commit() {
        // TODO Auto-generated method stub
        
    }
    
    /* 
     * @see com.dongnao.jack.transation.Transation#rollback()
     */
    @Override
    public void rollback() {
        // TODO Auto-generated method stub
        
    }
    
    /* 
     * @see com.dongnao.jack.transation.Transation#close()
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }
    
}
