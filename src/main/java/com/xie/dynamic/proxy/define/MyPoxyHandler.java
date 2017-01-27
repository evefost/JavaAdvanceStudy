package com.xie.dynamic.proxy.define;

import java.lang.reflect.Method;

/**
 * Created by xieyang on 17/1/21.
 */
public class MyPoxyHandler<T> implements MyInvocationHandler {

    private T target;

    public MyPoxyHandler(T target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        Object invoke =  method.invoke(target, null);
        after();
        return invoke;
    }

    //扩展模块
    private void before() {
        System.out.println("自定义代理...要做饭了,你想吃什么");
    }

    private void after() {
        System.out.println("自定义代理... 吃完了,收拾一下吧");
    }
}

