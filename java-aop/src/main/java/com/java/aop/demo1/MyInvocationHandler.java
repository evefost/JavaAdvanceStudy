package com.java.aop.demo1;

import com.java.aop.framework.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
      
    // 目标对象   
    private Object target;

    private Advice advice;

      
    /** 
     * 构造方法 
     * @param target 目标对象  
     */  
    public MyInvocationHandler(Object target,Advice advice) {
        super();  
        this.target = target;
        this.advice = advice;
    }  
  
  
    /** 
     * 执行目标对象的方法 
     */  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          
        // 在目标对象的方法执行之前简单的打印一下  
        //System.out.println("------------------before------------------");
          advice.beforeMethod(target,method,args);
        // 执行目标对象的方法  
        Object result = method.invoke(target, args);  
          
        // 在目标对象的方法执行之后简单的打印一下  
       // System.out.println("-------------------after------------------");
        advice.afterMethod(target,method,args);
        return result;  
    }  
  
    /** 
     * 获取目标对象的代理对象 
     * @return 代理对象 
     */  
    public Object getProxy() {  
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);  
    }  
}  
  

