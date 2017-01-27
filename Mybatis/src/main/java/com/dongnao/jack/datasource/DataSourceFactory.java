/** 
 * @Project     DN-jack-mybatis 
 * @File        DataSourceFactory.java 
 * @Package     com.dongnao.jack.datasource 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午9:34:44 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.datasource;

/** 
 * @Description TODO 
 * @ClassName   DataSourceFactory 
 * @Date        2016年10月27日 下午9:34:44 
 * @Author      dongnao.jack 
 */

public interface DataSourceFactory {
    
    DataSource getDataSource();
    
}
