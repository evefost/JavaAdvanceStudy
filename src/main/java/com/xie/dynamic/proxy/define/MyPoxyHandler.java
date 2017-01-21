package com.xie.dynamic.proxy.define;

import com.xie.dynamic.proxy.People;

import java.lang.reflect.Method;

/**
 * Created by xieyang on 17/1/21.
 */
public class MyPoxyHandler implements MyInvocationHandler {

    private People people;

    public MyPoxyHandler(People people) {
        this.people = people;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        method.invoke(people, null);
        after();
        return null;
    }

    //扩展模块
    private void before() {
        System.out.println("自定义...要做饭了,你想吃什么");
    }

    private void after() {
        System.out.println("自定义... 吃完了,收拾一下吧");
    }
}

