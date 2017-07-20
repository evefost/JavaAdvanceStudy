package com.xie.aop.by_proxy;

import org.aspectj.lang.annotation.Around;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SleepHelper implements MethodBeforeAdvice, AfterReturningAdvice {
  
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
        System.out.println("睡觉前要脱衣服！");  
    }  
  
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {  
        System.out.println("起床后要穿衣服！");  
    }  
  
}  