package com.xie.mybatis2.mapperProxy;

/**
 * Created by xieyang on 17/1/27.
 */
public class MapperImpl {

    public static <T> void getPoxy(String key) {

        //T people = (T) Proxy.newProxyInstance(MapperImpl.class.getClassLoader(), new Class[]{T.class}, new MapperInvocationHandler(new Zhansan()));

    }

}
