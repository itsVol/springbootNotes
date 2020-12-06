package cn.learn;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public abstract class AbstractCache<K,V> implements Cache<K,V>{
    private static final long serialVersionUID=1L;
    protected Map<K,CacheObj<K,V>>cacheMap;
    //TODO :learn ReentrantReadWriteLock
    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private final ReadLock readLock=cacheLock.readLock();
    private final WriteLock writeLock= cacheLock.writeLock();

    protected int capacity;
    protected long timeout;
    protected boolean existCustomTimeout;
    protected int hitCount;
    protected int missCount;

    @Override
    public void put(K key, V object) {
        put(key,object,timeout);
    }

    @Override
    public void put(K key, V object, long timeout) {
        writeLock.lock();
        try{
            putWithoutLock(key,object,timeout);
        }finally {
            writeLock.unlock();
        }
    }
    protected abstract int pruneCache();
    private void putWithoutLock(K key,V object,long timeout){
        CacheObj<K,V> co = new CacheObj<>(key, object,timeout);
        if (timeout !=0){
            existCustomTimeout=true;
        }
        if (isFull()){
            pruneCache();
        }
        cacheMap.put(key,co);
    }

    @Override
    public boolean isContainKey(K key) {
        readLock.lock();
        try{
            final CacheObj<K,V> co = cacheMap.get(key);
            if (co ==null){
                return false;
            }
            if (!co.isExpired()){
                return true;
            }

        }finally {
            readLock.unlock();
        }
        remove(key,true);// remove expired by myself
        return false;
    }


    public int getHitCount() {
        this.readLock.lock();
        try {
            return hitCount;
        }finally {
            this.readLock.unlock();
        }

    }

    public int getMissCount() {
        this.readLock.lock();
        try {
            return missCount;
        }finally {
            this.readLock.unlock();
        }

    }

    @Override
    public V get(K key) {
        return get(key,true);
    }

    @Override
    public V get(K key, boolean isUpdateAccess) {
        readLock.lock();
        try {
            final CacheObj<K,V> co =cacheMap.get(key);
            if (co==null){
                missCount++;
                return null;
            }
            if (!co.isExpired()){
                hitCount++;
                return co.get(isUpdateAccess);
            }
        }finally {
            readLock.unlock();
        }
        remove(key,true);
        return null;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public long timeout() {
        return timeout;
    }
    protected boolean isPruneExpiredActive() {
        this.readLock.lock();
        try {
            return (timeout != 0) || existCustomTimeout;
        } finally {
            this.readLock.unlock();
        }
    }
    @Override
    public boolean isFull() {
        this.readLock.lock();
        try {
            return (capacity > 0) && (cacheMap.size() >= capacity);
        } finally {
            this.readLock.unlock();
        }
    }
    @Override
    public void remove(K key) {
        remove(key, false);
    }
    @Override
    public void clear() {
        writeLock.lock();
        try {
            cacheMap.clear();
        } finally {
            writeLock.unlock();
        }
    }
    @Override
    public int size() {
        this.readLock.lock();
        try {
            return cacheMap.size();
        } finally {
            this.readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        this.readLock.lock();
        try {
            return cacheMap.isEmpty();
        } finally {
            this.readLock.unlock();
        }
    }
    /**
     * 对象移除回调。默认无动作
     *
     * @param key 键
     * @param cachedObject 被缓存的对象
     */
    protected void onRemove(K key, V cachedObject) {
        // ignore
    }

    private void remove(K key, boolean withMissCount) {
        writeLock.lock();
        CacheObj<K, V> co;
        try {
            co = removeWithoutLock(key, withMissCount);
        } finally {
            writeLock.unlock();
        }
        if (null != co) {
            onRemove(co.key, co.obj);
        }
    }
    private CacheObj<K, V> removeWithoutLock(K key, boolean withMissCount) {
        final CacheObj<K, V> co = cacheMap.remove(key);
        if (withMissCount) {
            // 在丢失计数有效的情况下，移除一般为get时的超时操作，此处应该丢失数+1
            this.missCount++;
        }
        return co;
    }
}

