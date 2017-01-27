package com.xie.mybatis2.sqlNode;

/**
 * Created by xieyang on 17/1/26.
 */
public class DynamicSqlNode implements SqlNode{
    private String test;

    public boolean appendTo(DynamicContext context) {
        return false;
    }
}
