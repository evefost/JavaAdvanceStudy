/** 
 * @Project     DN-jack-mybatis 
 * @File        ConnectionHandler.java 
 * @Package     com.dongnao.jack.connectionHandler 
 * @Version     V1.0 
 * @Date        2016年10月31日 下午9:01:52 
 * @Author      dongnao.jack 
 */

package com.xie.mybatis2.connectionHandler;


import com.xie.mybatis2.sqlNode.BoundSql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/** 
 * @Description 是jdbc中connecttion的一个代理 
 * @ClassName   ConnectionHandler 
 * @Date        2016年10月31日 下午9:01:52 
 * @Author      dongnao.jack 
 */

public class ConnectionHandler implements InvocationHandler {
    
    private Connection connection;
    
    private BoundSql boundsql;
    
    public ConnectionHandler(Connection connection, BoundSql boundsql) {
        this.connection = connection;
        this.boundsql = boundsql;
    }
    

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        
        System.out.println("execute by : " + method.getName());
        System.out.println("SQL is : [" + boundsql.getSql() + "]");
        
        return method.invoke(connection, args);
    }
    
}
