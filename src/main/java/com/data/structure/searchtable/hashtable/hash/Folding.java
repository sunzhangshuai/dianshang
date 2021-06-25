package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * Folding:
 * 折叠法
 *
 * @author sunchen
 * @date 2020/7/13 12:10 上午
 */
public class Folding extends AbstractHashFun {
    private static final Folding FOLDING = new Folding();

    private Folding() {
    }

    public static Folding getInstance() {
        return FOLDING;
    }

    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        Integer intKey = getHashCode(key);
        int tableLength = hashTable.tableLength();
        int size = String.valueOf(tableLength).length();
        int base = 1;
        while (size > 0) {
            size--;
            base *= 10;
        }
        int sum = 0;
        while (intKey > tableLength) {
            sum += intKey % base;
            intKey = intKey / base;
        }
        sum += intKey;
        return (sum % String.valueOf(tableLength).length()) % tableLength;
    }
}
