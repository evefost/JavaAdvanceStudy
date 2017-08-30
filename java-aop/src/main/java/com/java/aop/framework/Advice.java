package com.java.aop.framework;

import java.lang.reflect.Method;

/**
 * 一般四种
 * 前，后，前后，异常
 */
public interface Advice {

    void beforeMethod(Object target, Method method, Object[] args);
    void afterMethod(Object target, Method method, Object[] args);
    void inException(Object target, Method method, Object[] args);
}
