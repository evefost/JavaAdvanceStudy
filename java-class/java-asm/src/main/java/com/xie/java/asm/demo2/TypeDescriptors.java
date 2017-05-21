package com.xie.java.asm.demo2;

import org.objectweb.asm.Type;

/**
 * .三。类型描述
 * 我们知道JAVA类型分为基本类型和引用类型，在JVM中对每一种类型都有与之相对应的类型描述，如下表：
 */
public class TypeDescriptors {
    public static void main(String[] args) {

        System.out.println(Type.getDescriptor(TypeDescriptors.class));
        System.out.println(Type.getDescriptor(String.class));
        System.out.println(Type.getDescriptor(boolean.class));
        System.out.println(Type.getDescriptor(Boolean.class));
        System.out.println(Type.getDescriptor(Object.class));
    }

}