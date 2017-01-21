package com.xie.reflect;

import java.io.*;

/**
 * Created by xieyang on 17/1/21.
 */
public class MyClassLoader extends ClassLoader{
    File baseDire;

    public MyClassLoader(String basePath){
        baseDire = new File(basePath);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        if(baseDire != null){
            String fileName = name.replace('.',File.separatorChar)+".class";
            System.out.println("load class:"+fileName);
            File classFile = new File(baseDire,fileName);
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                     in = new FileInputStream(classFile);
                     out =new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }
                    return  defineClass(name,out.toByteArray(),0,out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }
}
