package com.xie.mybatis2.sqlNode;

/**
 * 最终解释出来的 sql
 */
public class BoundSql {

    private String sql;

    public BoundSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


}
