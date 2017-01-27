/** 
 * @Project     DN-jack-mybatis 
 * @File        Configuration.java 
 * @Package     com.dongnao.jack.configTemplate 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午9:13:11 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.configTemplate;

import java.util.HashMap;
import java.util.Map;

/** 
 * @Description TODO 
 * @ClassName   Configuration 
 * @Date        2016年10月25日 下午9:13:11 
 * @Author      dongnao.jack 
 */

public class Configuration {
    
    Map<String, MappedStatement> MappedStatements = new HashMap<String, MappedStatement>();
    
    Map<String, ResultMap> resultMaps = new HashMap<String, ResultMap>();
    
    Environment environment;
    
    public Map<String, MappedStatement> getMappedStatements() {
        return MappedStatements;
    }
    
    public void setMappedStatements(
            Map<String, MappedStatement> mappedStatements) {
        MappedStatements = mappedStatements;
    }
    
    public Map<String, ResultMap> getResultMaps() {
        return resultMaps;
    }
    
    public void setResultMaps(Map<String, ResultMap> resultMaps) {
        this.resultMaps = resultMaps;
    }
    
    public Environment getEnvironment() {
        return environment;
    }
    
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
