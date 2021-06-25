package com.data.structure.searchtable.interfaces;

/**
 * @author sunchen
 */
public interface DynamicSearchTable<T> {
    /**
     * 根据关键字key查找元素
     * @param key
     * @return
     */
    T search (Object key);

    /**
     * 如果不存在关键字等于
     * @param e
     * @return
     */
    boolean insert (T e);

    /**
     * 删除关键字为key的记录
     * @param key
     * @return
     */
    boolean delete (Object key);

    /**
     * 遍历
     */
    void traverse();
}
