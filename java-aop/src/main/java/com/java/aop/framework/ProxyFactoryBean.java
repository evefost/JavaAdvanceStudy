package com.java.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/8/28.
 */
public class ProxyFactoryBean {

    private Advice advice;

    private Object target;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy(){

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //处理advice
                advice.beforeMethod(target,method,args);
                //调用目标类实例方法
                Object invoke = method.invoke(target,args);
                advice.afterMethod(target,method,args);
                return invoke;
            }
        });
        return proxy ;
    }
}
