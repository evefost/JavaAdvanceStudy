package com.phei.netty.sync.future;

import com.phei.netty.sync.msg.Respone;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.*;


public class SyncFuture implements WriteFuture<Respone> {


    public static Map<Long, WriteFuture> FUTURES = new ConcurrentHashMap<Long, WriteFuture>();


    private CountDownLatch latch = new CountDownLatch(1);

    private final long begin = System.currentTimeMillis();


    private long timeout;

    private Respone response;

    private final long requestId;


    private boolean isTimeout = false;

    public SyncFuture(long requestId) {
        this.requestId = requestId;
        FUTURES.put(requestId, this);
    }


    public void setResponse(Respone response) {
        this.response = response;
        latch.countDown();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return response != null;
    }

    public Respone get() throws InterruptedException, ExecutionException {
        //无限期等待
        latch.wait();
        return response;
    }

    public Respone get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout, unit)) {
            return response;
        }
        return null;
    }

    public boolean isTimeout() {
        if (isTimeout) {
            return isTimeout;
        }
        return System.currentTimeMillis() - begin > timeout;
    }

    public static void received(Channel channel, Respone response) {
        WriteFuture future = FUTURES.remove(response.getId());
        if (future != null) {
            future.setResponse(response);
        } else {

        }
    }
}
