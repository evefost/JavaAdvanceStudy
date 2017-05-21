package com.xie.java.asm.demo1.xsd.defined.demo1;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class LastNameBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    //spring 解释得到类的
    protected Class getBeanClass(Element element) {
        return School.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("lastname");
        String email = element.getAttribute("email");

        if (StringUtils.hasText(userName)) {
            bean.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(email)) {
            bean.addPropertyValue("email", email);
        }
    }
}

