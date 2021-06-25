package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * MedianSquare:
 * 平方取中法
 *
 * @author sunchen
 * @date 2020/7/13 12:10 上午
 */
public class MedianSquare extends AbstractHashFun {
    private static final MedianSquare MEDIAN_SQUARE = new MedianSquare();

    private MedianSquare() {
    }

    public static MedianSquare getInstance() {
        return MEDIAN_SQUARE;
    }

    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        Integer intKey = getHashCode(key);
        String strKey = String.valueOf(intKey);
        int size = String.valueOf(hashTable.tableLength()).length();
        if (strKey.length() < size) {
            return intKey;
        }
        strKey = strKey.substring(0, size);
        intKey = (int) Math.pow(Integer.parseInt(strKey), 2);
        strKey = String.valueOf(intKey);
        strKey = strKey.substring((strKey.length() + size) / 2 - size, (strKey.length() + size) / 2);
//        System.out.println( strKey + "--------" + hashTable.tableLength());
        return Integer.parseInt(strKey) %  hashTable.tableLength();
    }
}
