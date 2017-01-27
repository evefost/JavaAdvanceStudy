/** 
 * @Project     DN-jack-mybatis 
 * @File        DataSource.java 
 * @Package     com.dongnao.jack.datasource 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:35:11 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.datasource;

import java.sql.Connection;

/** 
 * @Description TODO 
 * @ClassName   DataSource 
 * @Date        2016年10月27日 下午9:35:11 
 * @Author      dongnao.jack 
 */

public interface DataSource {
    
    public Connection getConnection();
    
}
