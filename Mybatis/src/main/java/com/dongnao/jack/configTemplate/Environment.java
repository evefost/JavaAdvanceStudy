/** 
 * @Project     DN-jack-mybatis 
 * @File        Environment.java 
 * @Package     com.dongnao.jack.configTemplate 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:26:46 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.configTemplate;

import com.dongnao.jack.datasource.DataSource;
import com.dongnao.jack.transation.TransationFactory;

/** 
 * @Description     <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments> 
 * @ClassName   Environment 
 * @Date        2016��10��25�� ����9:26:46 
 * @Author      dongnao.jack 
 */

public class Environment {
    
    private DataSource dataSource;
    
    private TransationFactory transationFactory;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public TransationFactory getTransationFactory() {
        return transationFactory;
    }
    
    public void setTransationFactory(TransationFactory transationFactory) {
        this.transationFactory = transationFactory;
    }
}
