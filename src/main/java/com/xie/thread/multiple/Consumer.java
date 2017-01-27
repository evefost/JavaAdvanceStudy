package com.xie.thread.multiple;

/**
 * 消费者
 */
class Consumer implements Runnable {
    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            try {
                //操作共享资源
                res.out();
            } catch (Exception e) {
            }
        }
    }
}
