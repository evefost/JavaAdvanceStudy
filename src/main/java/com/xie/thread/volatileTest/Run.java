package com.xie.thread.volatileTest;

/**
 * Created by xieyang on 17/5/30.
 */
public class Run {


    public static void main(String[] args) throws InterruptedException {
        PrintString ps = new PrintString();
        new Thread(ps).start();
        System.out.println("我要停止它");
        ps.setIscontinuePrint(false);

    }
}
