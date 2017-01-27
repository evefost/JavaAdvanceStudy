package com.xie.mybatis2.sqlNode;

import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class DynamicSqlSource implements SqlSource {

    private List<SqlNode> sqlNodes;

    public DynamicSqlSource(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
