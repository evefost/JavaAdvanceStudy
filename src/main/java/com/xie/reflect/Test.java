package com.xie.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xieyang on 17/1/21.
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        try {
            Class<?> clazz = Class.forName("com.xie.reflect.Student");
            Object student = clazz.newInstance();
            System.out.println("student====" + student);

            Constructor constructor = clazz.getConstructor();
//            Object student2 = clazz.newInstance();
//            System.out.println("student2====" + student2);
//
//            Method studyHard = clazz.getMethod("studyHard", new Class[]{});
//            studyHard.invoke(student, null);
//            Method studyHard2 = clazz.getMethod("studyHard", new Class[]{String.class});
//            studyHard2.invoke(student, "zzzzzzz");
//            Method studyHard2 = clazz.getMethod("studyHard", new Class[]{String.class, int.class});
//            studyHard2.invoke(student, "zzzzzzz", 22);

//                for(Method method:clazz.getMethods()){
//                    System.out.println("方法====" + method.getName()+method.getGenericParameterTypes());
//                }
            Field name = clazz.getSuperclass().getDeclaredField("name");
            name.setAccessible(true);
            name.set(student,"tom");
            System.out.println("name:"+name.get(student));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
