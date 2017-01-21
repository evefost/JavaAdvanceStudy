package com.xie.dynamic.proxy.define;

import java.lang.reflect.Method;

/**
 * 自定义动态处理接口
 * 规范我们的处理类的行为
 * Created by xieyang on 17/1/21.
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
