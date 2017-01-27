package com.xie.thread.multiple;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    //创建个Condition对象，这两个对象是绑定到产生自己的锁对象的。
    private Condition condition_pro = lock.newCondition();//控制生产者
    private Condition condition_con = lock.newCondition();//控制消费者

    public void set(String name) throws InterruptedException {
        lock.lock(); //锁上
        try {
            while (flag)
                //当前线程等待，直到别的线程执行condition_pro.signal()
                condition_pro.await();
            this.name = name + "--" + count++;
            System.out.println(Thread.currentThread().getName()
                    + "...生产者..." + this.name);
            flag = true;
            //唤醒在这个条件下等待的其他线程
            //(也就是执行了condition_con.await()的线程)
            condition_con.signal();
        } finally {
            lock.unlock(); //开锁 一定要执行
        }
    }

    public void out() throws InterruptedException {
        lock.lock();
        try {
            while (!flag)
                //当前线程等待，直到别的线程执行
                condition_con.await();
                System.out.println(Thread.currentThread().getName()
                    + "..... 消费者......" + this.name);
            flag = false;
            condition_pro.signal();
        } finally {
            lock.unlock(); //开锁 一定要执行
        }
    }
}
