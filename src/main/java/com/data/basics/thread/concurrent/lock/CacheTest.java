package com.data.basics.thread.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Cache:
 * 缓存系统
 * @author sunchen
 * @date 2020/7/3 2:47 下午
 */
public class CacheTest {


}

class Cache{
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Map<String,String > cacheMap = new HashMap<String, String>();
    String value = "";
    public String get(String key){
       //如果有直接返回，
        lock.readLock().lock();
        try {
            String value = cacheMap.get(key);
            if (value == null) {
                //如果没有从数据库获取
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "ffff";
                        cacheMap.put(key, value);
                    }
                    lock.readLock().lock();
                }finally {
                    lock.writeLock().unlock();
                }
            }
            return value;
        }catch (Exception e) {
            throw new RuntimeException("");
        }finally {
            lock.readLock().unlock();
        }
    }
}
