package com.xie.java.asm.demo2;

import org.objectweb.asm.Type;

/**
 * ASM系列之二：Java类的基本表述
 * 二、内部名字：
 * 在Java二进制文件中使用的是JVM的内部名字，而不是我们所熟悉的以“.”分割的全限定名，
 * 内部名字是以“/”替代“.”的全名，
 * 例如：java.lang.String在JVM中的内部名字是java/lang/String。
 * 在ASM中可以使用org.objectweb.asm.Type类中的静态方法getInternalName(final Class c)
 * 来获得，如下：
 */
public class InternalNameTransform {

    public static void main(String[] args) {
        System.out.println(Type.getInternalName(String.class));
        System.out.println(Type.getInternalName(Integer.class));
        System.out.println(Type.getInternalName(Boolean.class));
        System.out.println(Type.getInternalName(InternalNameTransform.class));
    }


}