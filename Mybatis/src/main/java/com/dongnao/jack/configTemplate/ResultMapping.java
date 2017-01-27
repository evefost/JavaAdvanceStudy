/** 
 * @Project     DN-jack-mybatis 
 * @File        ResultMapping.java 
 * @Package     com.dongnao.jack.configTemplate 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午9:19:32 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.configTemplate;

/** 
 * @Description<result column="PSPTID" property="psptId" jdbcType="VARCHAR" javaType="string" /> 
 * @ClassName   ResultMapping 
 * @Date        2016年10月25日 下午9:19:32 
 * @Author      dongnao.jack 
 */

public class ResultMapping {
    
    private String column;
    
    private String property;
    
    private String jdbcType;
    
    private String javaType;
    
    public String getColumn() {
        return column;
    }
    
    public void setColumn(String column) {
        this.column = column;
    }
    
    public String getProperty() {
        return property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }
    
    public String getJdbcType() {
        return jdbcType;
    }
    
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
    
    public String getJavaType() {
        return javaType;
    }
    
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
    
}
