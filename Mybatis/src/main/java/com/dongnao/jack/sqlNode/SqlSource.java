/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlSource.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����11:03:06 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description ��ȡ���ǵ�BoundSql���� 
 * @ClassName   SqlSource 
 * @Date        2016��10��25�� ����11:03:06 
 * @Author      dongnao.jack 
 */

public interface SqlSource {
    
    BoundSql getBoundSql(Object param);
    
}
