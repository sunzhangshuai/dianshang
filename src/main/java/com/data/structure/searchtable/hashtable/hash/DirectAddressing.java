package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * DirectAddressing:
 * 直接寻址法
 *
 * @author sunchen
 * @date 2020/7/13 12:12 上午
 */
public class DirectAddressing extends AbstractHashFun {
    private static final DirectAddressing DIRECT_ADDRESSING = new DirectAddressing();

    private DirectAddressing() {
    }

    public static DirectAddressing getInstance() {
        return DIRECT_ADDRESSING;
    }

    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        Integer intKey = getHashCode(key);
        return (intKey - hashTable.base) % hashTable.tableLength();
    }
}
