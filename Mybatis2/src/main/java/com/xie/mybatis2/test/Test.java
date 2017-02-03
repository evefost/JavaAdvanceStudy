package com.xie.mybatis2.test;

import com.xie.mybatis2.session.SqlSession;
import com.xie.mybatis2.session.SqlSessionFactory;
import com.xie.mybatis2.session.SqlSessionFactoryBuilder;
import com.xie.mybatis2.testBean.TestBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyang on 17/1/26.
 */
public class Test {

    public static void main(String[] args) {

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.buildSqlSessionFactory("SqlMapConfig.xml");
        SqlSession sqlSession = sqlSessionFactory.createSqlSession();
        selectList(sqlSession);
    }

    private static void selectList(SqlSession sqlSession) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", "2");
        List<TestBean> list = sqlSession.selectList("com.xie.mybatis2.dao.TestMapper.selectList", paramMap);
        System.out.println("" + list);
        for (TestBean bean : list) {
            System.out.println("id:" + bean.getId() + "====name:" + bean.getName());
        }
    }

    private static void selectOne(SqlSession sqlSession) {

    }

}
