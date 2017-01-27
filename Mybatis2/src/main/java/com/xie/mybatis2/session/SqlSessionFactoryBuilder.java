package com.xie.mybatis2.session;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.parse.XMLConfigParser;

import java.io.InputStream;

/**
 * 加载配置,创建session工厂
 */
public class SqlSessionFactoryBuilder {


    /**
     * 创建session工厂
     * @param path  配置文件路径
     * @return
     */
    public SqlSessionFactory buildSqlSessionFactory(String path){
        String basePath = getClass().getClassLoader().getResource("").getPath();
        //path = basePath+path;
        InputStream is = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(path);
        XMLConfigParser parser = new XMLConfigParser();
        Configuration config = null;
        try {
            config = parser.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new DefualtSessionFactory(config);
        return sqlSessionFactory;

    }

}
