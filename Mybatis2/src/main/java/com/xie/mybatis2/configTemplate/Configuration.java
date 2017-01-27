package com.xie.mybatis2.configTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieyang on 17/1/26.
 */
public class Configuration {

    //增删改查的sql
    private Map<String,MappedStatement> statementMap = new HashMap<String, MappedStatement>();

    //返回对象的map
    private Map<String,ResultMap> resultMapMap = new HashMap<String, ResultMap>();

    private Enviroment enviroment;


    public Map<String, MappedStatement> getStatementMap() {
        return statementMap;
    }

    public void setStatementMap(Map<String, MappedStatement> statementMap) {
        this.statementMap = statementMap;
    }

    public Map<String, ResultMap> getResultMapMap() {
        return resultMapMap;
    }

    public void setResultMapMap(Map<String, ResultMap> resultMapMap) {
        this.resultMapMap = resultMapMap;
    }

    public Enviroment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Enviroment enviroment) {
        this.enviroment = enviroment;
    }
}
