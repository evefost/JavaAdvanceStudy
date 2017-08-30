package com.java.aop.advice;

import com.java.aop.framework.Advice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/29.
 */
public class MyAdvice implements Advice {
    @Override
    public void beforeMethod(Object target, Method method, Object[] args) {
        System.out.println("方法执行前.....");
    }

    @Override
    public void afterMethod(Object target, Method method, Object[] args) {
        System.out.println("方法执行后");
    }

    @Override
    public void inException(Object target, Method method, Object[] args) {
        System.out.println("在异常里执行");
    }
}
