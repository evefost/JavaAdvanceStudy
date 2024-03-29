package com.xie.java.asm.demo3;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * ASM系列之三：ASM中的访问者模式
 */
public interface ClassVisitor {

    void visit(int version, int access, String name, String signature, String superName, String[] interfaces);

    void visitSource(String source, String debug);

    void visitOuterClass(String owner, String name, String desc);

    AnnotationVisitor visitAnnotation(String desc, boolean visible);

    void visitAttribute(Attribute attr);

    void visitInnerClass(String name, String outerName, String innerName, int access);

    FieldVisitor visitField(int access, String name, String desc, String signature, Object value);

    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions);

    void visitEnd();
}