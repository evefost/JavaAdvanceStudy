package com.xie.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by xieyang on 17/2/3.
 */
public class Wechat extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channale" + channel + ":" + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channale:" + channel + ":" + subscribedChannels);
    }

    public static void main(String[] args) {
        Jedis je = null;
        try {
            Wechat wc = new Wechat();
            je = new Jedis("172.16.165.128", 6379, 0);
            //订阅
            wc.proceed(je.getClient(), "dongnao-java");
        } catch (Exception e) {
            System.err.println("redis ex:" + e.toString());
        } finally {
            if (je != null) {
                je.disconnect();
            }
        }


    }
}
