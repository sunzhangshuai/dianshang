package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * RandomProbing:
 * 随机线性探测
 *
 * @author sunchen
 * @date 2020/7/12 8:14 下午
 */
public class RandomProbing extends Probing {
    @Override
    public int next(int index, int conflict, AbstractHashTable hashTable, Object key) {
        int length = hashTable.hashSize[hashTable.sizeIndex];
        int random = (int) (Math.random() * length);
        return Math.floorMod(index + random, length);
    }
}
