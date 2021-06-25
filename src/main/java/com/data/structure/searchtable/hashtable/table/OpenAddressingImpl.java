package com.data.structure.searchtable.hashtable.table;

import com.data.structure.searchtable.hashtable.confictmanage.ConflictManage;
import com.data.structure.searchtable.hashtable.hash.HashFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * OpenAddressingImpl:
 * 开放定址hash表
 *
 * @author sunchen
 * @date 2020/7/12 8:15 下午
 */
public class OpenAddressingImpl extends AbstractHashTable{
    public OpenAddressingImpl(ConflictManage conflictManage, HashFunction hashFunction){
        super(conflictManage, hashFunction);
    }

    @Override
    public void traverse() {

        HashMap hashMap = new HashMap();
        Collections.synchronizedMap(hashMap);

    }

    public static void main(String[] args) {

    }
}
