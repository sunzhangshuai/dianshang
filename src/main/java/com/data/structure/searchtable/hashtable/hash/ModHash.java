package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * ModHash:
 *
 * @author sunchen
 * @date 2020/7/12 4:25 下午
 */
public class ModHash extends AbstractHashFun {
    private static final ModHash MOD_HASH = new ModHash();

    private ModHash() {
    }

    public static ModHash getInstance() {
        return MOD_HASH;
    }

    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        int divide = prime(hashTable.tableLength());
        Integer intKey = getHashCode(key);
        return Math.floorMod(intKey, divide);
    }

    /**
     * 取 小于number最小质数
     *
     * @param number
     * @return
     */
    public int prime(int number) {
        int start = 2;
        for (int i = number; i > 0; i--) {
            boolean status = true;
            for (int j = start; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    status = false;
                    break;
                }
            }
            if (status) {
                return i;
            }
        }
        throw new RuntimeException("没有找到不大于data的素数");
    }
}
