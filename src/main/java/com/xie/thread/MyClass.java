package com.xie.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xieyang on 17/1/27.
 */
public class MyClass {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void b() {

        System.out.println("sleep 前线程名:" + Thread.currentThread().getName() + "==" + sdf.format(new Date()));
        try {
            Thread.sleep(3000);

            System.out.println("sleep后===== 前线程名:" + Thread.currentThread().getName() + "==" + sdf.format(new Date()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
