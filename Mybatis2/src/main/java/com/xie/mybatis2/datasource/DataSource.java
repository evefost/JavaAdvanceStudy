package com.xie.mybatis2.datasource;

import java.sql.Connection;

/**
 * Created by xieyang on 17/1/26.
 */
public interface DataSource {

    Connection getConnection();

}
