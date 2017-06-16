package com.xie.thread.volatileTest;

/**
 * Created by xieyang on 17/5/30.
 */
public class PrintString implements Runnable {

    private boolean iscontinuePrint = true;

    public boolean isIscontinuePrint() {
        return iscontinuePrint;
    }

    public void setIscontinuePrint(boolean iscontinuePrint) {
        this.iscontinuePrint = iscontinuePrint;
    }

    @Override
    public void run() {
        printMehtod();
    }

    private void printMehtod() {
        while (iscontinuePrint == true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
