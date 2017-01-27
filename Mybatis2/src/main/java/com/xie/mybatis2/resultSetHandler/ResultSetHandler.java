package com.xie.mybatis2.resultSetHandler;

import java.sql.Statement;
import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public interface ResultSetHandler {

    <T> List<T> resultSetHandle(Statement stmt);
}
