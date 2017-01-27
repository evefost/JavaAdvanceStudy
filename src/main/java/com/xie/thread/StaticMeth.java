package com.xie.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xieyang on 17/1/27.
 */
public class StaticMeth {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static synchronized void a() {
        System.out.println("线程名aaa==" + Thread.currentThread().getName() + "========" + sdf.format(new Date()));
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void b() {
        System.out.println("线程名bbbb==" + Thread.currentThread().getName() + "========" + sdf.format(new Date()));
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
