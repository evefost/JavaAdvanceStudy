package com.xie.mybatis2.configTemplate;

import com.xie.mybatis2.datasource.DataSource;
import com.xie.mybatis2.transation.TransationFactory;

public class Enviroment {

    private DataSource dataSource;
    private TransationFactory transationFactory;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TransationFactory getTransationFactory() {
        return transationFactory;
    }

    public void setTransationFactory(TransationFactory transationFactory) {
        this.transationFactory = transationFactory;
    }
}
