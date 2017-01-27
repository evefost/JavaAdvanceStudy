package com.xie.pools;

/**
 * Created by xieyang on 17/1/25.
 */
public class PoolManager {

    private static class createPool {
        private static MyPoolImpl myPool = new MyPoolImpl();
    }

    public static MyPoolImpl getInstance() {
        return createPool.myPool;
    }
}
