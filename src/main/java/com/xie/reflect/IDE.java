package com.xie.reflect;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by xieyang on 17/1/21.
 */
public class IDE {
   static String ln = "\r\n";

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String packageName = IDE.class.getPackage().getName();
        String className = "Tom";

        StringBuffer sb = new StringBuffer();
        sb.append("package  "+packageName+";"+ln);
        sb.append("public class ").append(className).append("{"+ln);
        sb.append("public String name;"+ln);
        sb.append("public void say(){"+ln);
        sb.append("System.out.println(\"老师正在说话\");"+ln);
        sb.append("}"+ln);
        sb.append("}"+ln);
        System.out.println(sb);
        String basePath = IDE.class.getResource("").getPath();

        String filePath = basePath+className+".java";

        System.out.println("filePath===="+filePath);
        //保存到磁盘
        File srcFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(srcFile);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = manager.getJavaFileObjects(srcFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();
        String classLongName = packageName+"."+className;
        Class clazz = new MyClassLoader(basePath.replaceAll(packageName.replace('.',File.separatorChar),"")).findClass(classLongName);
        Object tom = clazz.newInstance();
        Method say = clazz.getMethod("say");
        say.invoke(tom);

    }
}
