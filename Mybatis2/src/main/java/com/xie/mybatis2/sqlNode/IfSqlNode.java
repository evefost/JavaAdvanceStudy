package com.xie.mybatis2.sqlNode;

import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private List<SqlNode> sqlNodes;

    public IfSqlNode(String test, List<SqlNode> sqlNodes) {
        this.test = test;
        this.sqlNodes = sqlNodes;
    }

    public boolean appendTo(DynamicContext context) {
        return false;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<SqlNode> getSqlNodes() {
        return sqlNodes;
    }

    public void setSqlNodes(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }
}
