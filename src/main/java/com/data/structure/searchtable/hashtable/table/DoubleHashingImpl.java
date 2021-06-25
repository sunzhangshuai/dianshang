package com.data.structure.searchtable.hashtable.table;

import com.data.structure.searchtable.hashtable.confictmanage.ConflictManage;
import com.data.structure.searchtable.hashtable.confictmanage.ReHash;
import com.data.structure.searchtable.hashtable.hash.HashFunction;

/**
 * DoubleHashing:
 * 二次哈希
 *
 * @author sunchen
 * @date 2020/7/12 10:22 下午
 */
public class DoubleHashingImpl extends AbstractHashTable{

    public DoubleHashingImpl(HashFunction hashFunction) {
        super(new ReHash(), hashFunction);
    }

    @Override
    public void traverse() {

    }
}
