package com.xie.mybatis2.mapperProxy;

import com.xie.mybatis2.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xieyang on 17/1/27.
 */
public class MapperInvocationHandler implements InvocationHandler {

    private String mapperClassName;
    private SqlSession sqlSession;

    public MapperInvocationHandler(SqlSession sqlSession, String mapperClassName) {
        this.sqlSession = sqlSession;
        this.mapperClassName = mapperClassName;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }
}
