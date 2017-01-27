/** 
 * @Project     DN-jack-mybatis 
 * @File        StaticTextSqlNode.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����10:55:28 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

/** 
 * @Description TODO 
 * @ClassName   StaticTextSqlNode 
 * @Date        2016��10��25�� ����10:55:28 
 * @Author      dongnao.jack 
 */

public class StaticTextSqlNode implements SqlNode {
    
    private String text;
    
    public boolean appendTo(DynamicContext context) {
        context.getSb().append(text);
        return false;
    }
    
    public StaticTextSqlNode(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
