package com.xie.hash;

/**
 * Created by xieyang on 17/1/29.
 */
public class HashMap<K, V> implements Map<K, V> {

    private int defaultLength = 16;

    private double defaultLoad = 0.75;
    private int size = defaultLength;

    private Entry<K, V>[] table = new Entry[defaultLength];

    public HashMap(int length, double load) {
        this.defaultLength = length;
        this.defaultLoad = load;
    }

    public HashMap() {

    }

    public V put(K key, V value) {
        //根据key,和函数
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            entry = new Entry<K, V>(key, value, null, index);
            table[index] = entry;
            size++;
        } else {
            Entry<K, V> newEntry = new Entry<K, V>(key, value, entry, index);
            table[index] = newEntry;

        }
        return table[index].getValue();
    }

    public V get(K key) {
        int index = getIndex(key);
        return table[index] == null ? null : table[index].getValue();
    }

    public int size() {
        return size();
    }

    private int getIndex(K key) {
        //除留下取余数法(取模)
        int m = defaultLength - 1;
        return key.hashCode() % m;
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        int index;

        public Entry(K key, V value, Entry<K, V> next, int index) {
            this.key = key;
            this.value = value;
            this.index = index;
            this.next = next;

        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
