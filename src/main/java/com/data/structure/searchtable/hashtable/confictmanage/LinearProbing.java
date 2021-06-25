package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * LinearProbing:
 * 线性探测
 *
 * @author sunchen
 * @date 2020/7/12 8:06 下午
 */
public class LinearProbing extends Probing {

    @Override
    public int next(int index, int conflict, AbstractHashTable hashTable, Object key) {
        int length = hashTable.hashSize[hashTable.sizeIndex];
        return Math.floorMod(index + 1, length);
    }
}
