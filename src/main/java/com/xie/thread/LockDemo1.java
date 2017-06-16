package com.xie.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xieyang on 17/5/29.
 */
public class LockDemo1 {

    public static void main(String[] args) {

        final Service service = new Service();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                service.methodA();
                //service.methodB();
            }
        };
        Thread[] arr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread(run);
        }
        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static class Service {

        public ReentrantLock lock = new ReentrantLock();

        public void methodA() {
            lock.lock();
            System.out.println("等等获取锁的thread:" + lock.getHoldCount());

            System.out.println(Thread.currentThread().getName() + " run methodAAA");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void methodB() {
            //lock.lock();
            System.out.println(Thread.currentThread().getName() + " run methodBBB");
            try {
                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }

        }


    }
}
