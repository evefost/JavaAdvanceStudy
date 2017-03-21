package com.xie.java.xsd.defined.demo1;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 2.自定义命名schema 处理器,
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        //如果有多种不种标签，都可以在这里注册
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }

}