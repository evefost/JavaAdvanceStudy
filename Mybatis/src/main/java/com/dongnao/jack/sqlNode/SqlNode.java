/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlNode.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����10:51:59 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description ��װsqlƬ�� 
 * @ClassName   SqlNode 
 * @Date        2016��10��25�� ����10:51:59 
 * @Author      dongnao.jack 
 */

public interface SqlNode {
    boolean appendTo(DynamicContext context);
}
