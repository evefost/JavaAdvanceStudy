/** 
 * @Project     DN-jack-mybatis 
 * @File        JdbcTransationFactory.java 
 * @Package     com.dongnao.jack.transation 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:26:40 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.transation;

import com.dongnao.jack.datasource.DataSource;

/** 
 * @Description 创建事务的实现类 
 * @ClassName   JdbcTransationFactory 
 * @Date        2016年10月27日 下午9:26:40 
 * @Author      dongnao.jack 
 */

public class JdbcTransationFactory implements TransationFactory {
    
    private DataSource dataSource;
    
    public JdbcTransationFactory() {
        
    }
    
    public JdbcTransationFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Transation createTransation(DataSource dataSource) {
        return new JdbcTransation(dataSource);
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
