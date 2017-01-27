package com.xie.pools;


/**
 * 定义连接池的动作规范
 * Created by xieyang on 17/1/25.
 */
public interface IMyPool {

    PooledConnection getConnection();

    void createConnections(int count);
}
