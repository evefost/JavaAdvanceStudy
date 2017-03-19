package com.xie.java.xsd.defined.demo1;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义命名schema 处理器
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
        registerBeanDefinitionParser("lastname", new LastNameBeanDefinitionParser());
        registerBeanDefinitionParser("age", new LastNameBeanDefinitionParser());
    }

}