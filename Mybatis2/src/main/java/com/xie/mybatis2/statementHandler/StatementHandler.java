/** 
 * @Project     DN-jack-mybatis 
 * @File        StatementHandler.java 
 * @Package     com.dongnao.jack.statementHandler 
 * @Version     V1.0 
 * @Date        2016年10月31日 下午8:48:06 
 * @Author      dongnao.jack 
 */

package com.xie.mybatis2.statementHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/** 
 * @Description 封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。 
 * @ClassName   StatementHandler 
 * @Date        2016年10月31日 下午8:48:06 
 * @Author      dongnao.jack 
 */

public interface StatementHandler {
    
    <T> List<T> query(Statement stmt) throws SQLException;
    
    int update(Statement stmt) throws SQLException;
    
    Statement createStatement(Connection connection) throws SQLException;
}
