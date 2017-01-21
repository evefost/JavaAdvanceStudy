package com.xie.reflect;

/**
 * Created by xieyang on 17/1/21.
 */
public class Student extends  Person implements  GoodStudent{

    String school;
    int clazz;
    float score;


    public void studyHard() {
        System.out.println("在好好学习");
    }

    public void studyHard(String book) {
        System.out.println("正在看书:"+book);
    }

    public void studyHard(String book,int core) {
        System.out.println("正在看书:"+book+core);
    }
}
