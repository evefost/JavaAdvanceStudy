/** 
 * @Project     DN-jack-mybatis 
 * @File        Transation.java 
 * @Package     com.dongnao.jack.transation 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:24:18 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.transation;

import java.sql.Connection;

/** 
 * @Description TODO 
 * @ClassName   Transation 
 * @Date        2016年10月27日 下午9:24:18 
 * @Author      dongnao.jack 
 */

public interface Transation {
    
    Connection getConnection();
    
    void commit();
    
    void rollback();
    
    void close();
    
}
