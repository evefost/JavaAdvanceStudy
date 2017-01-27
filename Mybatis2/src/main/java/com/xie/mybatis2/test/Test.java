package com.xie.mybatis2.test;

import com.xie.mybatis2.session.SqlSession;
import com.xie.mybatis2.session.SqlSessionFactory;
import com.xie.mybatis2.session.SqlSessionFactoryBuilder;
import com.xie.mybatis2.testBean.TestBean;

import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class Test {

    public static void main(String[] args) {

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.buildSqlSessionFactory("SqlMapConfig.xml");
        SqlSession sqlSession = sqlSessionFactory.createSqlSession();
        List<TestBean> list = sqlSession.selectList("com.xie.mybatis2.dao.TestMapper.queryContent", null);
        System.out.println("" + list);
    }
}
