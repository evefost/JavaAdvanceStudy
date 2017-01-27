package com.xie.dynamic.proxy;

import com.xie.dynamic.proxy.define.MyPoxyHandler;
import com.xie.dynamic.proxy.define.MyProxy;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by xieyang on 17/1/21.
 */
public class Test {

    public static  void main(String[] args){
       //创建一个代理类
        System.out.println("自定义动态代理类==========:");
        try {
            People people2 = MyProxy.newProxyInstance(People.class.getClassLoader(), People.class, new MyPoxyHandler<People>(new Zhansan()));
            people2.eat();
            Animal animal =  MyProxy.newProxyInstance(Animal.class.getClassLoader(), Animal.class, new MyPoxyHandler<Animal>(new Cat()));
            animal.eat();
            if(animal.catchMouse()){
                System.out.println("抓到了老...");
            }else {
                System.out.println("没抓到了老...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        System.out.println();
        System.out.println();

//        System.out.println("jdk动态代理类========");
//        People people = (People) Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, new ProxyHandler(new Zhansan()));
//
//        try {
//            people.eat();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        System.out.println("动态代理类名字:"+people.getClass().getName());
//        createProxyClassFile();
    }

    //将运行时期动态代理对象提取出,保存到当前项目路下
    public static  void createProxyClassFile(){
        //获取内存中的代理类$Proxy0
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{People.class});
        //通过数组字节流的形式保存项目文件
        try {
            FileOutputStream out = new FileOutputStream("$Proxy0.class");
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
