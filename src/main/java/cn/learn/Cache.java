package cn.learn;

import java.io.Serializable;

public interface Cache<K,V> extends Iterable<V>, Serializable {
    /**
     *
     * @return return capacity of cache; <code>0</code> indicate non-restrict;
     */
    int capacity();

    /**
     *
     * @return  timeout in cache
     */
    long timeout();

    /**
     * add object in cache,use default timeout
     * @param key
     * @param object
     */
    void put(K key,V object);

    void put (K key,V object,long timeout);

    /**
     *
     * @param key
     * @param object
     * @param timeout check time between last time and this call. And reset time.
     * @return object which you want
     */
    V get(K key ,V object,long timeout);

    V get(K key);
    V get(K key,boolean isUpdateAccess);

    /**
     * prune timeout element.
     * @return
     */
    int prune();
    boolean isFull();
    void remove(K key);
    void clear();
    int size();
    boolean isEmpty();
    boolean isContainKey(K key);

}

