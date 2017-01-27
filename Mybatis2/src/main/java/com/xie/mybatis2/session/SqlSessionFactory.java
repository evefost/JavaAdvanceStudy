package com.xie.mybatis2.session;

import com.xie.mybatis2.configTemplate.Configuration;

/**
 * Created by xieyang on 17/1/26.
 */
public interface SqlSessionFactory {

    SqlSession createSqlSession();
}
