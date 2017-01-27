package com.xie.dynamic.proxy;

/**
 * Created by xieyang on 17/1/25.
 */
public class Cat implements Animal {
    public void eat() {
        System.out.println("cat eat fish");
        //return "cat eat fish";
    }

    public Boolean catchMouse() throws Throwable {
        System.out.println("cat catchMouse ....");
        return true;
    }
}
