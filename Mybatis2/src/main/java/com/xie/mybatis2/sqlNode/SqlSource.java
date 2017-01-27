package com.xie.mybatis2.sqlNode;

/**
 * Created by xieyang on 17/1/26.
 */
public interface SqlSource {

    BoundSql getBoundSql(Object param);
}
