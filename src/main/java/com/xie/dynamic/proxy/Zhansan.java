package com.xie.dynamic.proxy;

import com.xie.reflect.Person;

/**
 * Created by xieyang on 17/1/21.
 */
public class Zhansan implements People {
    public void eat() throws Throwable {
        System.out.println("张三 eat");
    }

    public void sleep() throws Throwable {
        System.out.println("张三 sleet");
    }

    public void dadoudou() throws Throwable {
        System.out.println("张三 dadoudou");
    }
}
