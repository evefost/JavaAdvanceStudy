package com.xie.java.asm.demo2;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;

/**
 * 四、方法描述：
 * 在Java的二进制文件中，方法的方法名和方法的描述都是存储在Constant pool中的，
 * 且在两个不同的单元里。
 * 因此，方法描述中不含有方法名，只含有参数类型和返回类型，如下：
 */
public class MethodDescriptors {
    public static void main(String[] args) throws Exception {
        Method m = String.class.getMethod("substring", int.class);
        System.out.println(Type.getMethodDescriptor(m));
    }

}
