package com.xie.java.asm.demo1.xsd.defined.test;

import com.xie.java.asm.demo1.xsd.defined.demo1.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test.xml");
        User user = (User) ac.getBean("testBean");
        System.out.println(user.getUserName());
    }

}
