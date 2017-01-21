package com.xie.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xieyang on 17/1/21.
 */
public class ProxyHandler implements InvocationHandler {

    People people = null;

    public ProxyHandler(People people) {
        this.people = people;
    }

    /**
     * 我们看得到的执行方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        method.invoke(people, null);
        after();
        return null;
    }

    //扩展模块
    private void before() {
        System.out.println("要做饭了,你想吃什么");
    }

    private void after() {
        System.out.println("吃完了,收拾一下吧");
    }
}
