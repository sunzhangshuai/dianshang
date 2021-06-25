package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import com.data.structure.searchtable.model.Element;

import java.util.HashMap;

/**
 * LinearProbing:
 * 线性探测
 *
 * @author sunchen
 * @date 2020/7/12 8:06 下午
 */
public class Probing implements ConflictManage {

    /**
     * @param hashTable
     * @param index     计算出的索引值
     * @param key       关键字
     * @return
     */
    @Override
    public Element search(AbstractHashTable hashTable, int index, Object key) {
        int newIndex = index;
        int conflict = 0;
        int maxConflict = getMaxConflict(hashTable);
        if (hashTable.data[newIndex] == null) {
            return null;
        } else {
            while (hashTable.data[newIndex] != null && conflict <= maxConflict) {
                Element element = (Element) hashTable.data[newIndex];
                if (element.equalKey(key) == 0 && !element.isDelete) {
                    return element;
                } else if (element.equalKey(key) == 0) {
                    return null;
                }
                conflict++;
                newIndex = next(newIndex, conflict, hashTable, key);
            }
        }
        return null;
    }

    @Override
    public boolean insert(AbstractHashTable hashTable, int index, Element element) {
        //要插入，首先要查一下有没有
        int newIndex = index;
        int conflict = 0;
        int maxConflict = getMaxConflict(hashTable);
        if (hashTable.data[newIndex] == null) {
            //直接插
            hashTable.data[newIndex] = element;
        } else {
            while (hashTable.data[newIndex] != null && conflict <= maxConflict) {
                Element record = (Element) hashTable.data[newIndex];
                if (record.equalKey(element.getKey()) == 0 && !element.isDelete) {
                    //重置
                    hashTable.data[newIndex] = element;
                    return true;
                } else if (record.equalKey(element.getKey()) == 0) {
                    //
                    hashTable.data[newIndex] = element;
                }
                conflict++;
                newIndex = next(newIndex, conflict, hashTable, element.getKey());
            }
            if (hashTable.data[newIndex] == null) {
                //直接插
                hashTable.data[newIndex] = element;
            }
            if (conflict > maxConflict) {
                //重建hash表
                hashTable.reCreateHashTable();
                //计算在新表中的索引值
                newIndex = hashTable.hashFunction.handle(hashTable, element.getKey());
                insert(hashTable, newIndex, element);
            }
        }
        return false;
    }

    @Override
    public boolean delete(AbstractHashTable hashTable, int index, Object key) {
        Element element = search(hashTable, index, key);
        if (element == null) {
            return false;
        }
        element.isDelete = true;
        return true;
    }

    public int next(int index, int conflict, AbstractHashTable hashTable, Object key) {
        int length = hashTable.hashSize[hashTable.tableLength()];
        return Math.floorMod(index + 1, length);
    }

    public int getMaxConflict(AbstractHashTable hashTable) {
        return hashTable.tableLength() / 2;
    }

    public static void main(String[] args) {
    }
}
