/** 
 * @Project     DN-jack-mybatis 
 * @File        Executor.java 
 * @Package     com.dongnao.jack.executor 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:16:54 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.executor;

import java.sql.SQLException;
import java.util.List;

import com.dongnao.jack.configTemplate.MappedStatement;

/** 
 * @Description MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护 
 * @ClassName   Executor 
 * @Date        2016年10月27日 下午10:16:54 
 * @Author      dongnao.jack 
 */

public interface Executor {
    
    <T> List<T> queryList(MappedStatement ms, Object param) throws SQLException;
    
    int update(MappedStatement ms, Object param) throws SQLException;
    
    void commit();
    
    void rollback();
}
