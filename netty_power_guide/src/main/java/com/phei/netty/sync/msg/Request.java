package com.phei.netty.sync.msg;

import java.util.concurrent.atomic.AtomicLong;


public class Request {

    private static final AtomicLong INVOKE_ID = new AtomicLong(0);

    private final long mId;

    //是否为同步
    private boolean isSync;

    private Object mData;

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public Request() {
        mId = newId();
    }

    private static long newId() {
        // getAndIncrement()增长到MAX_VALUE时，再增长会变为MIN_VALUE，负数也可以做为ID
        return INVOKE_ID.getAndIncrement();
    }

    public long getId() {
        return mId;
    }


    public Object getData() {
        return mData;
    }

    public void setData(Object msg) {
        this.mData = msg;
    }

}
