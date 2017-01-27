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
    static String proxyName = "$Proxy0";

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
    public static <T> T newProxyInstance(ClassLoader classLoader, Class intef, MyInvocationHandler h) throws IllegalArgumentException {

        if (null == h) {
            throw new NullPointerException();
        }

        //实际运行的动态代理类构造一个java对象
        Method[] methods = intef.getMethods();
        StringBuffer prxySb = new StringBuffer();
        String packageName = MyProxy.class.getPackage().getName();//intef.getPackage().getName();

        prxySb.append("package " + packageName + ";" + rt)
                .append("import java.lang.reflect.Method;" + rt)
                .append("public  class $Proxy0 extends " + MyProxy.class.getName() + " implements " + intef.getName() + " {" + rt)
                .append("public $Proxy0(" + MyInvocationHandler.class.getName() + "  h)  {" + rt)
                .append("super(h);}" + rt)
                .append(createMethodS(methods, intef))
                .append("}" + rt);

        System.out.println(prxySb.toString());
        //加载字节码
        Class clazz = null;
        try {
            clazz = getProxyClass0(prxySb, packageName);
            Constructor constructor = clazz.getConstructor(MyInvocationHandler.class);
            if (constructor != null) {
                return (T) constructor.newInstance(h);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {

        }

        return null;
    }

    private static Class getProxyClass0(StringBuffer sb, String packageName) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        String basePath = MyProxy.class.getResource("").getPath();
        String filePath = basePath + proxyName + ".java";
        System.out.println("filePath====" + filePath);
        //保存到磁盘
        File srcFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(srcFile);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();

        //编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable iterable = manager.getJavaFileObjects(srcFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();
        //删源文件
        File javaFile = new File(basePath + proxyName + ".java");
        if (javaFile.exists()) {
            javaFile.delete();
        }
        //加载.class文件字节码
        String classLongName = packageName + "." + proxyName;
        Class clazz = new MyClassLoader(basePath.replaceAll(packageName.replace('.', File.separatorChar), "")).findClass(classLongName);
        //删字节码文件
        File clazzFile = new File(basePath + proxyName + ".class");
        if (clazzFile.exists()) {
            clazzFile.delete();
        }
        return clazz;
    }


    private static String createMethodS(Method[] methods, Class interf) {
        String methodsString = "";
        for (Method method : methods) {
            String returnTypeString = method.getReturnType().getName();
            methodsString = methodsString
                    + "public  " + returnTypeString + " " + method.getName() + "() throws Throwable {" + rt
                    + "Method md = " + interf.getName() + ".class.getMethod(\""
                    + method.getName() + "\",new Class[]{});" + rt;
            if ("void".equals(returnTypeString)) {
                methodsString = methodsString
                        + "super.h.invoke(this,md,null);" + rt + "}" + rt;
            } else {
                methodsString = methodsString
                        + "return ("+returnTypeString+")super.h.invoke(this,md,null);" + rt + "}" + rt;
            }

        }
        return methodsString;
    }
}
