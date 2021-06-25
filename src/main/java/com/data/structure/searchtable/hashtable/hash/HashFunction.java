package com.data.structure.searchtable.hashtable.hash;


import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * @author sunchen
 * hash函数
 */
public interface HashFunction {
    /**
     * hash
     * @param hashTable
     * @param key
     * @return
     */
    int handle(AbstractHashTable hashTable, Object key);


}
