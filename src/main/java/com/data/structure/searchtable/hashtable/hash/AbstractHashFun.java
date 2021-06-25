package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * HashFun:
 *
 * @author sunchen
 * @date 2020/7/13 1:39 上午
 */
abstract public class AbstractHashFun implements HashFunction {

    /**
     * 处理
     * @param hashTable
     * @param key
     * @return
     */
    @Override
    abstract public int handle(AbstractHashTable hashTable, Object key);

    /**
     * 获取hashcode
     * @param key
     * @return
     */
    public Integer getHashCode(Object key) {
        int result;
        if (key instanceof String) {
            result = key.hashCode();
        } else {
            result = (Integer) key;
        }
        return result;
    }
}
