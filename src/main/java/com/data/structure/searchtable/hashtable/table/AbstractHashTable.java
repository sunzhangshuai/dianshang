package com.data.structure.searchtable.hashtable.table;

import com.data.structure.searchtable.hashtable.confictmanage.ConflictManage;
import com.data.structure.searchtable.hashtable.hash.HashFunction;
import com.data.structure.searchtable.interfaces.DynamicSearchTable;
import com.data.structure.searchtable.model.Element;
import com.data.structure.util.PrimeUtil;

/**
 * HashTable:
 *
 * @author sunchen
 * @date 2020/7/12 4:34 下午
 */
public abstract class AbstractHashTable implements DynamicSearchTable<Element> {
    int initSize = 100;

    int incrementSize = 100;

    int maxSize = 1000;
    /**
     * hashSize中的下标，可以获取当前数组的大小
     */
    public int sizeIndex;

    /**
     * 处理冲突的函数
     */
    public ConflictManage conflict;

    /**
     * hash函数
     */
    public HashFunction hashFunction;

    /**
     * 数组
     */
    public Object[] data;

    /**
     * 容量数组
     */
    public int[] hashSize;

    /**
     * 用于hash函数计算的基地址
     */
    public int base;

    public AbstractHashTable(ConflictManage conflict, HashFunction hashFunction) {
        initHashSize();
        this.conflict = conflict;
        this.hashFunction = hashFunction;
        this.data = new Object[tableLength()];
    }

    /**
     * 查找
     *
     * @param key
     * @return
     */
    @Override
    public Element search(Object key) {
        int hashIndex = hashFunction.handle(this, key);
        return conflict.search(this, hashIndex, key);
    }

    /**
     * 插入
     *
     * @param e
     * @return
     */
    @Override
    public boolean insert(Element e) {
        int hashIndex = hashFunction.handle(this, e.getKey());
        return conflict.insert(this, hashIndex, e);
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(Object key) {
        int hashIndex = hashFunction.handle(this, key);
        return conflict.delete(this, hashIndex, key);
    }

    /**
     * 遍历hash表
     */
    @Override
    abstract public void traverse();

    /**
     * 初始化容量数据
     */
    protected void initHashSize() {
        hashSize = new int[100];
        int index = 0;
        for (int i = initSize; i <= maxSize; i += incrementSize) {
            hashSize[index++] = PrimeUtil.prime(i);
        }
    }

    /**
     * 重建hash表
     */
    public void reCreateHashTable() {
        Object[] newData = new Object[hashSize[++sizeIndex]];
        Object[] temp = data;
        data = newData;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null && ((Element) temp[i]).isDelete) {
                //计算位置，放入hash表中
                int hashIndex = hashFunction.handle(this, ((Element) data[i]).getKey());
                conflict.insert(this, hashIndex, ((Element) data[i]));
            }
        }
    }

    /**
     * 获取表长
     *
     * @return
     */
    public int tableLength() {
        return hashSize[sizeIndex];
    }
}
