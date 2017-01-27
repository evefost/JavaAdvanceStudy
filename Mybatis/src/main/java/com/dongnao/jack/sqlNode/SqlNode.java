/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlNode.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午10:51:59 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description 封装sql片段 
 * @ClassName   SqlNode 
 * @Date        2016年10月25日 下午10:51:59 
 * @Author      dongnao.jack 
 */

public interface SqlNode {
    boolean appendTo(DynamicContext context);
}
