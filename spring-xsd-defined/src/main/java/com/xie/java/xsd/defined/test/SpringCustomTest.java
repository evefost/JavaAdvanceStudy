package com.xie.java.xsd.defined.test;

import com.xie.java.xsd.defined.demo1.School;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test.xml");
        School user = (School) ac.getBean("testBean");
//		System.out.println(user.getUserName());
//		DataSourceInfo d = (DataSourceInfo) context.getBean("myDataSourcce");
//		System.out.println(d);
    }

}
