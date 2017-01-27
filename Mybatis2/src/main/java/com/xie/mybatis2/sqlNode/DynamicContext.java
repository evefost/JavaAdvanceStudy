/** 
 * @Project     DN-jack-mybatis 
 * @File        DynamicContext.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����10:53:31 
 * @Author      dongnao.jack 
 */

package com.xie.mybatis2.sqlNode;

/**
 *
 */
public class DynamicContext {
    
    private StringBuffer sb;
    
    private Object param;
    
    public DynamicContext(StringBuffer sb, Object param) {
        this.sb = sb;
        this.param = param;
    }
    
    public StringBuffer getSb() {
        return sb;
    }
    
    public void setSb(StringBuffer sb) {
        this.sb = sb;
    }
    
    public Object getParam() {
        return param;
    }
    
    public void setParam(Object param) {
        this.param = param;
    }
}
