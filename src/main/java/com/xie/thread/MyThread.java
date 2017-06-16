package com.xie.thread;

/**
 * Created by xieyang on 17/1/27.
 */
public class MyThread  extends Thread {

    public static  void  main(String[] args){
        MyClass myClass = new MyClass();
        MyThread myThread1 = new MyThread(myClass);
        MyThread myThread2 = new MyThread(myClass);
        myThread1.start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread2.start();


    }


    MyClass myClass;
    public MyThread(MyClass myClass){
        this.myClass = myClass;
    }
    @Override
    public void run() {
        while (true){
            myClass.b();
        }
    }
}
