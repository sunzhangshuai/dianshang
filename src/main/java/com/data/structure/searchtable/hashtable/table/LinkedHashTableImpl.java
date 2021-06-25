package com.data.structure.searchtable.hashtable.table;

import com.data.structure.searchtable.hashtable.confictmanage.Linked;
import com.data.structure.searchtable.hashtable.hash.HashFunction;

/**
 * LinkedHashTableImpl:
 *
 * @author sunchen
 * @date 2020/7/12 4:47 下午
 */
public class LinkedHashTableImpl extends AbstractHashTable {

    public LinkedHashTableImpl(HashFunction hashFunction) {
        super(new Linked(), hashFunction);
    }

    @Override
    public void traverse() {

    }
}
