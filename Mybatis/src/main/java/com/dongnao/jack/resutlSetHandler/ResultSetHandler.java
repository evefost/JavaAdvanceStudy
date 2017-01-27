/** 
 * @Project     DN-jack-mybatis 
 * @File        ResultSetHandler.java 
 * @Package     com.dongnao.jack.resutlSetHandler 
 * @Version     V1.0 
 * @Date        2016年10月31日 下午9:13:35 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.resutlSetHandler;

import java.sql.Statement;
import java.util.List;

/** 
 * @Description 负责将JDBC返回的ResultSet结果集对象转换成List类型的集合 
 * @ClassName   ResultSetHandler 
 * @Date        2016年10月31日 下午9:13:35 
 * @Author      dongnao.jack 
 */

public interface ResultSetHandler {
    <T> List<T> resultSetHandle(Statement stmt);
}
