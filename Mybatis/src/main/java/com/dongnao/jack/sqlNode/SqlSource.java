/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlSource.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午11:03:06 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description 获取我们的BoundSql对象 
 * @ClassName   SqlSource 
 * @Date        2016年10月25日 下午11:03:06 
 * @Author      dongnao.jack 
 */

public interface SqlSource {
    
    BoundSql getBoundSql(Object param);
    
}
