package com.xie.reflect;

import java.lang.reflect.*;

/**
 * Created by xieyang on 17/1/21.
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        //buildObjectByConstructors();
        //invokeMethods();

        reflectFieldInfos();
    }


    /**
     * 1.通过不同的构造创建实例
     *
     * @throws ClassNotFoundException
     */

    private static void buildObjectByConstructors() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Student> clazz = (Class<Student>) Class.forName("com.xie.reflect.Student");
        //获取所有构造函数
        Constructor[] constructors = clazz.getConstructors();
        for(Constructor c:constructors){
            Class[] parameterTypes = c.getParameterTypes();
            String clsNames ="";
            for(Class cl : parameterTypes){
                clsNames+=cl.getName()+",";
            }
            System.out.println("constructor name["+c.getName()+"] parameterTypes["+clsNames);
        }
        System.out.println();

        //1.默认无参构造,如果没有默认构造将抛出异常
        Student student1 = clazz.newInstance();
        Constructor<Student> defualtConstructor = clazz.getConstructor(null);
        Student student2 = defualtConstructor.newInstance();
        System.out.println();

        //调用有参构造函数
        Constructor<Student> constructor1 = clazz.getConstructor(String.class);
        Student xieyang = constructor1.newInstance("xieyang");
        Constructor<Student> constructor2 = clazz.getConstructor(String.class, float.class);
        Student xieyang2 = constructor2.newInstance("xieyang2", 1234);

    }

    /**
     * 调用方法
     * @throws ClassNotFoundException
     */
    private static void invokeMethods() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Student> clazz = (Class<Student>) Class.forName("com.xie.reflect.Student");
        System.out.println("获取所有方法信息");
        Method[] methods = clazz.getMethods();
        for(Method m:methods){
            Class<?>[] parameterTypes = m.getParameterTypes();
            String clsNames ="";
            for(Class cl : parameterTypes){
                clsNames+=cl.getName()+",";
            }
            System.out.println("methdo name["+m.getName()+"] parameterTypes["+clsNames);
        }

        System.out.println();
        System.out.println("调用无参数方法");
        Student student = clazz.newInstance();
        Method studyHard1 = clazz.getMethod("studyHard");
        studyHard1.invoke(student);

        System.out.println();
        System.out.println("调用有参数方法");
        Method studyHard2 = clazz.getMethod("studyHard",String.class);
        studyHard2.invoke(student,"abc");

        Method studyHard3 = clazz.getMethod("studyHard",String.class,float.class);
        studyHard3.invoke(student,"efg",100);


    }

    private static void reflectFieldInfos() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Student> clazz = (Class<Student>) Class.forName("com.xie.reflect.Student");
        System.out.println("获取所有属性信息");
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f:declaredFields){
            System.out.println("field name["+f.getName()+" field type["+f.getType());
        }

        Student student = clazz.newInstance();
        Field score = clazz.getDeclaredField("score");
        score.setAccessible(true);
        score.set(student,123f);
        System.out.println("score:"+score.get(student));
    }
    
    

}
