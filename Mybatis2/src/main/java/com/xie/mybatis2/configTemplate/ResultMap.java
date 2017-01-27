package com.xie.mybatis2.configTemplate;

import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class ResultMap {

    private String id;

    private String type;

    //影射的类
    private Class<?> typeClass;

    private List<ResultMapping> idResultMapping;

    private List<ResultMapping> resultMapping;

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

    public List<ResultMapping> getIdResultMapping() {
        return idResultMapping;
    }

    public void setIdResultMapping(List<ResultMapping> idResultMapping) {
        this.idResultMapping = idResultMapping;
    }

    public List<ResultMapping> getResultMapping() {
        return resultMapping;
    }

    public void setResultMapping(List<ResultMapping> resultMapping) {
        this.resultMapping = resultMapping;
    }
}
