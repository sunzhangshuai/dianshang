package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import com.data.structure.searchtable.model.Element;

/**
 * @author sunchen
 */
public interface ConflictManage {
    /**
     * 查找
     *
     * @param hashTable
     * @param index
     * @param key
     * @return
     */
    Element search(AbstractHashTable hashTable, int index, Object key);

    /**
     * 插入
     *
     * @param hashTable
     * @param index
     * @param element
     * @return
     */
    boolean insert(AbstractHashTable hashTable, int index, Element element);

    /**
     * 删除
     *
     * @param hashTable
     * @param index
     * @param key
     * @return
     */
    boolean delete(AbstractHashTable hashTable, int index, Object key);
}
