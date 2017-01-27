package com.xie.design.responsibility;

/**
 * Created by xieyang on 17/1/23.
 */
public class Test {

    public static  void main(String[] args){
        HuNan huNan = new HuNan();
        HuBei huBei = new HuBei();
        Guangdong guangdong = new Guangdong();
        JianXi jianXi = new JianXi();

        huNan.setNextAction(huBei);
        huBei.setNextAction(jianXi);
        jianXi.setNextAction(guangdong);
        huNan.todo("江西");

    }
}
