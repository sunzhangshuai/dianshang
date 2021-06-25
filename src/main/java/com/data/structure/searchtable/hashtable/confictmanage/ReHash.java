package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.searchtable.hashtable.hash.DigitalAnalysis;
import com.data.structure.searchtable.hashtable.hash.DirectAddressing;
import com.data.structure.searchtable.hashtable.hash.Folding;
import com.data.structure.searchtable.hashtable.hash.HashFunction;
import com.data.structure.searchtable.hashtable.hash.MedianSquare;
import com.data.structure.searchtable.hashtable.hash.ModHash;
import com.data.structure.searchtable.hashtable.hash.RandomNumber;
import com.data.structure.searchtable.hashtable.table.AbstractHashTable;

/**
 * ReHash:
 *
 * @author sunchen
 * @date 2020/7/12 10:22 下午
 */
public class ReHash extends Probing {

    HashFunction[] hashList;

    public ReHash() {
        hashList = new HashFunction[]{
                DigitalAnalysis.getInstance(),
                DirectAddressing.getInstance(),
                Folding.getInstance(),
                MedianSquare.getInstance(),
                MedianSquare.getInstance(),
                ModHash.getInstance(),
                RandomNumber.getInstance()
        };
    }

    @Override
    public int getMaxConflict(AbstractHashTable hashTable) {
        return hashList.length-1;
    }

    @Override
    public int next(int index, int conflict, AbstractHashTable hashTable, Object key) {
        HashFunction hashFunction = hashList[conflict];
        return hashFunction.handle(hashTable, key);
    }
}
