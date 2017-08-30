package com.java.aop.demo1;

import com.java.aop.advice.MyAdvice;

public class ProxyTest {
  

    public static void main(String[] args) throws Throwable {
        // 实例化目标对象  
        UserService userService = new UserServiceImpl();  
          
        // 实例化InvocationHandler  
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService,new MyAdvice());
          
        // 根据目标对象生成代理对象  
        UserService proxy = (UserService) invocationHandler.getProxy();  
          
        // 调用代理对象的方法  
        proxy.add();  
          
    }  
}  