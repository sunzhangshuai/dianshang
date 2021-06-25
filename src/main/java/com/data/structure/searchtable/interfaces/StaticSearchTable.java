package com.data.structure.searchtable.interfaces;


/**
 * 查找表
 * @author sunchen
 */
public interface StaticSearchTable<T> {
    /**
     * 根据关键字key查找元素
     * @param key
     * @return
     */
    T search (Object key);

    /**
     * 遍历
     */
    void traverse();
}
