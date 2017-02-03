package com.xie.hash;

/**
 * Created by xieyang on 17/1/29.
 */
public interface Map<K, V> {

    V put(K key, V value);

    V get(K key);

    int size();


    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

}

