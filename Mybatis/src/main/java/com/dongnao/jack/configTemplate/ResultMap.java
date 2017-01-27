/** 
 * @Project     DN-jack-mybatis 
 * @File        ResultMap.java 
 * @Package     com.dongnao.jack.configTemplate 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午9:18:06 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.configTemplate;

import java.util.List;

/** 
 * @Description 就是解析封装我们的mapper配置文件中的resultMap
 * @ClassName   ResultMap 
 * @Date        2016年10月25日 下午9:18:06 
 * @Author      dongnao.jack 
 */

public class ResultMap {
    
    private String id;
    
    private String type;
    
    private Class<?> typeClass;
    
    List<ResultMapping> idResultMappings;
    
    List<ResultMapping> resultMappings;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Class<?> getTypeClass() {
        return typeClass;
    }
    
    public void setTypeClass(Class<?> typeClass) {
        this.typeClass = typeClass;
    }
    
    public List<ResultMapping> getIdResultMappings() {
        return idResultMappings;
    }
    
    public void setIdResultMappings(List<ResultMapping> idResultMappings) {
        this.idResultMappings = idResultMappings;
    }
    
    public List<ResultMapping> getResultMappings() {
        return resultMappings;
    }
    
    public void setResultMappings(List<ResultMapping> resultMappings) {
        this.resultMappings = resultMappings;
    }
}
