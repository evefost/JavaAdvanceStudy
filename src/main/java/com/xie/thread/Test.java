package com.xie.thread;

import java.io.FileOutputStream;

/**
 * Created by xieyang on 17/1/27.
 */
public class Test {

    public static void main(String[] args) {
        testStaticSynchronizedMethod();


    }

    public static  void testStaticSynchronizedMethod(){
        new TestThread().start();
        new TestThread2().start();

    }

   static  class TestThread extends Thread{
       @Override
       public void run() {
           while (true){
               StaticMeth.a();
           }

       }
   }

    static  class TestThread2 extends Thread{
        @Override
        public void run() {
            //while (true){
            StaticMeth.b();
            //}

        }
    }

}
