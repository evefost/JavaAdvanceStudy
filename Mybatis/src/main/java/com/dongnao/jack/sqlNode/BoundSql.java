/** 
 * @Project     DN-jack-mybatis 
 * @File        BoundSql.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����11:03:40 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description TODO 
 * @ClassName   BoundSql 
 * @Date        2016��10��25�� ����11:03:40 
 * @Author      dongnao.jack 
 */

public class BoundSql {
    
    String sql;
    
    public BoundSql(String sql) {
        this.sql = sql;
    }
    
    public String getSql() {
        return sql;
    }
    
    public void setSql(String sql) {
        this.sql = sql;
    }
    
}
