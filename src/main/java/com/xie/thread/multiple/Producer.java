package com.xie.thread.multiple;

/**
 * 生產者
 */
class Producer implements Runnable {
    private Resource res;

    Producer(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            try {
                //操作共享资源
                res.set("+商品+");
            } catch (Exception e) {
            }
        }
    }
}
