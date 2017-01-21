package com.xie.dynamic.proxy.define;

import com.xie.reflect.MyClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换jdk提供的代理类
 */
public class MyProxy {

    static String rt = "\r\n";
    static String  proxyName="$Proxy0";

    protected MyInvocationHandler h;

    /**
     * Prohibits instantiation.
     */
    private MyProxy() {
    }


    protected MyProxy(MyInvocationHandler h) {
        this.h = h;
    }

    //創建一個內豐當中的$Proxy0实例
    public static Object newProxyInstance(ClassLoader classLoader, Class intef, MyInvocationHandler h) throws IllegalArgumentException, IOException {
        if (null == h) {
            throw new NullPointerException();
        }
        //实际运行的动态代理类构造一个java对象
        System.out.println("构造一个java对象=========");
        Method[] methods = intef.getMethods();
        StringBuffer prxySb = new StringBuffer();
        String packageName = MyProxy.class.getPackage().getName();//intef.getPackage().getName();

        prxySb.append("package "+packageName+";" + rt)
                .append("import java.lang.reflect.Method;" + rt)
                .append("public  class $Proxy0  implements "+intef.getName()+" {" + rt)
                .append("public MyInvocationHandler h  ;" + rt)
                .append("public $Proxy0(MyInvocationHandler h)  {" + rt)
                .append("this.h = h;}" + rt)
                .append(createMethodS(methods, intef))
                .append("}" + rt);

        System.out.println(prxySb.toString());

        try {
           return  loadClass(prxySb,packageName,h);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object loadClass(StringBuffer sb, String packageName,MyInvocationHandler h) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        String basePath = MyProxy.class.getResource("").getPath();
        String filePath = basePath+proxyName+".java";
        System.out.println("filePath===="+filePath);
        //保存到磁盘
        File srcFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(srcFile);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();

        //编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = manager.getJavaFileObjects(srcFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();

        //加载.class文件字节码
        String classLongName = packageName+"."+proxyName;
        Class clazz = new MyClassLoader(basePath.replaceAll(packageName.replace('.',File.separatorChar),"")).findClass(classLongName);
       //不能用,默认的构(上面也没创建默认构造),因为什么传入InvocationHandler,或通过设
        //Object newInstance = clazz.newInstance();
        Constructor constructor = clazz.getConstructor(MyInvocationHandler.class);
        Object newInstance =  constructor.newInstance(h);
        return newInstance;

    }


    private static String createMethodS(Method[] methods, Class interf) {
        String methodsString = "";
        for (Method method : methods) {
            methodsString = methodsString
                    + "public  void " + method.getName() + "() throws Throwable {" + rt
                    + "Method md = " + interf.getName() + ".class.getMethod(\""
                    + method.getName() + "\",new Class[]{});" + rt
                    + "this.h.invoke(this,md,null);" + rt + "}" + rt;
        }
        return methodsString;
    }
}
