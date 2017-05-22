package com.phei.netty.sync.future;


import com.phei.netty.sync.msg.Respone;

import java.util.concurrent.Future;

/**
 * Created by fuzhengwei1 on 2016/10/20.
 */
public interface WriteFuture<T> extends Future<T> {

    void setResponse(Respone response);

    boolean isTimeout();


}
