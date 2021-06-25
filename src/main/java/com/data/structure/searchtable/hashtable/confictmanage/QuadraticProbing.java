package com.data.structure.searchtable.hashtable.confictmanage;


import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * QuadraticProbing:
 * 二次线性探测
 *
 * @author sunchen
 * @date 2020/7/12 8:13 下午
 */
public class QuadraticProbing extends Probing {

    @Override
    public int next(int index, int conflict, AbstractHashTable hashTable, Object key) {
        int length = hashTable.hashSize[hashTable.sizeIndex];
        index += (int) (conflict % 2 == 0 ? Math.pow(Math.ceil((double) conflict / 2), 2) * (-1) : Math.pow(Math.ceil((double) conflict / 2), 2));
        if (index >= 0) {
            return index % length;
        } else {
            return (length - Math.abs(index)) % length;
        }
    }
}
