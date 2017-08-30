package com.java.aop;

import com.java.aop.framework.BeanFactory;
import com.java.aop.testclass.TestInterface;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/29.
 */
public class ProxyTest {

    public static void main(String[] args){
        InputStream ips = ProxyTest.class.getResourceAsStream("config.properties");
        TestInterface bean = (TestInterface) new BeanFactory(ips).getBean("xxx");
        //Object bean = new BeanFactory(ips).getBean("");
        bean.targetMethod();
        //System.out.println(bean.getClass().getName());
    }
}