package com.dongnao.jack.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dongnao.jack.session.SqlSession;
import com.dongnao.jack.session.SqlSessionFactory;
import com.dongnao.jack.session.SqlSessionFactoryBuilder;
import com.dongnao.jack.testBean.ConsultContent;

public class Test {
    
    public static void main(String[] args) {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build("/com/dongnao/jack/config/SqlMapConfig.xml");
        SqlSession session = sessionFactory.createSqlSession();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("type", "1");
        List<ConsultContent> list = session.selectList("com.consult.dao.CommonMapper.queryContent",
                paramMap);
        
        System.out.print(list);
    }
}
