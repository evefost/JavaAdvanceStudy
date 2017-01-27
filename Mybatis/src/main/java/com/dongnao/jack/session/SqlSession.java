/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlSession.java 
 * @Package     com.dongnao.jack.session 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:34:57 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.session;

import java.sql.Connection;
import java.util.List;

/** 
 * @Description ʵ���Ͼ�����ɾ�Ĳ� 
 * @ClassName   SqlSession 
 * @Date        2016��10��25�� ����9:34:57 
 * @Author      dongnao.jack 
 */

public interface SqlSession {
    
    <T> T selectOne(String sql);
    
    <T> T selectOne(String sql, Object param);
    
    <T> List<T> selectList(String sql, Object param);
    
    int insert(String sql);
    
    int insert(String sql, Object param);
    
    int update(String sql);
    
    int update(String sql, Object param);
    
    int delete(String sql);
    
    int delete(String sql, Object param);
    
    void commit();
    
    void rollback();
    
    Connection getConnection();
}
