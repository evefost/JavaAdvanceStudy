/** 
 * @Project     DN-jack-mybatis 
 * @File        DefaultSqlSessionFactory.java 
 * @Package     com.dongnao.jack.session 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:11:15 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.session;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.datasource.DataSource;
import com.dongnao.jack.executor.Executor;
import com.dongnao.jack.executor.SimpleExecutor;
import com.dongnao.jack.transation.Transation;
import com.dongnao.jack.transation.TransationFactory;

/** 
 * @Description TODO 
 * @ClassName   DefaultSqlSessionFactory 
 * @Date        2016年10月27日 下午10:11:15 
 * @Author      dongnao.jack 
 */

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    
    private Configuration config;
    
    public DefaultSqlSessionFactory(Configuration config) {
        this.config = config;
    }
    
    public SqlSession createSqlSession() {
        
        TransationFactory tf = config.getEnvironment().getTransationFactory();
        
        DataSource source = config.getEnvironment().getDataSource();
        
        Transation ts = tf.createTransation(source);
        
        Executor executor = new SimpleExecutor(config, ts);
        
        return new DefaultSqlSession(config, executor);
    }
    
}
