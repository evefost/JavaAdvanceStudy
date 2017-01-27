package com.xie.mybatis2.session;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.datasource.DataSource;
import com.xie.mybatis2.executor.Executor;
import com.xie.mybatis2.executor.SimpleExecutor;
import com.xie.mybatis2.transation.Transation;
import com.xie.mybatis2.transation.TransationFactory;

/**
 * Created by xieyang on 17/1/26.
 */
public class DefualtSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefualtSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession createSqlSession() {

        TransationFactory factory = configuration.getEnviroment().getTransationFactory();
        DataSource dataSource = configuration.getEnviroment().getDataSource();
        Transation transation = factory.createTransation(dataSource);
        Executor executor = new SimpleExecutor(configuration,transation);
        return new DefualtSqlSession(configuration,executor);
    }
}
