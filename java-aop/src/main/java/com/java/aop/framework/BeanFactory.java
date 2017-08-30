package com.java.aop.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/8/28.
 */
public class BeanFactory {

    Properties props = new Properties();

    public BeanFactory(InputStream ips) {
        try {
            props.load(ips);
        } catch (IOException e) {

        }
    }

    public Object getBean(String name) {
        String className = props.getProperty(name);
        Object bean = null;
        try {
            Class clazz = Class.forName(className);
            bean = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bean instanceof ProxyFactoryBean) {
            Object proxy = null;
            try {
                ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
                Advice advice = (Advice) Class.forName(props.getProperty(name + ".advice")).newInstance();
                Object target = Class.forName(props.getProperty(name + ".target")).newInstance();
                proxyFactoryBean.setAdvice(advice);
                proxyFactoryBean.setTarget(target);
                //创建代理对象
                proxy = proxyFactoryBean.getProxy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return proxy;
        }
        return bean;
    }
}
