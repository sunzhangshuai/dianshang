package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * DigitalAnalysis:
 *
 * @author sunchen
 * @date 2020/7/13 12:11 上午
 */
public class DigitalAnalysis extends AbstractHashFun {
    private static final DigitalAnalysis DIGITAL_ANALYSIS = new DigitalAnalysis();

    private DigitalAnalysis() {
    }

    public static DigitalAnalysis getInstance() {
        return DIGITAL_ANALYSIS;
    }

    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        Integer intKey = getHashCode(key);
        String strKey = String.valueOf(intKey);
        int size = String.valueOf(hashTable.tableLength()).length();
        strKey = strKey.substring((strKey.length() + size) / 2 - size, size);
        return Integer.parseInt(strKey);
    }
}
