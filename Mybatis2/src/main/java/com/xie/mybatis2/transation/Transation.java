package com.xie.mybatis2.transation;

import java.sql.Connection;

/**
 * Created by xieyang on 17/1/26.
 */
public interface Transation {

    Connection getConnection();

    void commit();

    void rollback();

    void close();
}
