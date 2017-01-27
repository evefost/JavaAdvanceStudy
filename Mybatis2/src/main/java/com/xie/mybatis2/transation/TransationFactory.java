package com.xie.mybatis2.transation;

import com.xie.mybatis2.datasource.DataSource;

/**
 * Created by xieyang on 17/1/26.
 */
public interface TransationFactory {
    Transation createTransation(DataSource dataSource);
}
