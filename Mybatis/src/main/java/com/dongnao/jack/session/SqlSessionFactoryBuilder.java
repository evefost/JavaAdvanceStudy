/** 
 * @Project     DN-jack-mybatis 
 * @File        SqlSessionFactoryBuilder.java 
 * @Package     com.dongnao.jack.session 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:33:05 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.session;

import java.io.InputStream;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.parser.XMLConfigParser;

/** 
 * @Description 1������xml 2������SqlSessionFactory����
 * @ClassName   SqlSessionFactoryBuilder 
 * @Date        2016��10��25�� ����9:33:05 
 * @Author      dongnao.jack 
 */

public class SqlSessionFactoryBuilder {
    
    public SqlSessionFactory build(String path) {
        
        InputStream is = SqlSessionFactoryBuilder.class.getResourceAsStream(path);
        
        XMLConfigParser parse = new XMLConfigParser();
        
        Configuration config = null;
        
        try {
            config = parse.parse(is);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //2获取sqlSessionFactory工厂
        
        return new DefaultSqlSessionFactory(config);
    }
}
