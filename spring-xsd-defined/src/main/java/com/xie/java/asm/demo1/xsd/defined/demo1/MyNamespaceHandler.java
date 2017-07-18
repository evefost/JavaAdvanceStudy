package com.xie.java.asm.demo1.xsd.defined.demo1;

import com.xie.java.asm.demo1.xsd.defined.demo1.bean.Other;
import com.xie.java.asm.demo1.xsd.defined.demo1.beandefinition.parser.OtherBeanDefinitionParser;
import com.xie.java.asm.demo1.xsd.defined.demo1.beandefinition.parser.SchoolBeanDefinitionParser;
import com.xie.java.asm.demo1.xsd.defined.demo1.beandefinition.parser.UserBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 2.自定义命名schema 处理器,
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        //如果有多种不种标签，都可以在这里注册（自定义标签 user,other）
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
        registerBeanDefinitionParser("other", new OtherBeanDefinitionParser(Other.class));

    }

}