package com.xie.java.asm.demo1.xsd.defined.demo1.beandefinition.parser;

import com.xie.java.asm.demo1.xsd.defined.demo1.bean.School;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class SchoolBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    //spring 解释得到类的
    protected Class getBeanClass(Element element) {
        return School.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        String  address= element.getAttribute("address");
        int  students= Integer.parseInt(element.getAttribute("students"));
        builder.addConstructorArgValue(name);
        builder.addConstructorArgValue(address);
        builder.addConstructorArgValue(students);


    }


}

