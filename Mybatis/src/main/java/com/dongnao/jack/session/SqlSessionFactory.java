/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlSessionFactory.java 
 * @Package     com.dongnao.jack.session 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午9:34:38 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.session;

/** 
 * @Description TODO 
 * @ClassName   SqlSessionFactory 
 * @Date        2016年10月25日 下午9:34:38 
 * @Author      dongnao.jack 
 */

public interface SqlSessionFactory {
    SqlSession createSqlSession();
}
