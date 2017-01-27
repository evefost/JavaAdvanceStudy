package com.xie.reflect;

/**
 * Created by xieyang on 17/1/21.
 */
public class Student extends  Person implements  GoodStudent{

    String school;
    int clazz;
    float score;
    public Student(){
       System.out.println("调用默认构造函数创建实例");
    }
    public Student(String name){
        this.name = name;
        System.out.println("调用Student(String name)构造函数创建实例name:"+name);
    }

    public Student(String name,float score){
        this.name = name;
        this.score = score;
        System.out.println("调用Student(String name,float score)构造函数创建实例name:"+name+"==score"+score);
    }

    public void studyHard() {
        System.out.println("无参方法在好好学习");
    }

    public void studyHard(String book) {
        System.out.println("正在看书:"+book);
    }

    public void studyHard(String book,float score) {
        System.out.println("正在看书:"+book+score);
    }
}
