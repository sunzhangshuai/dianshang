package com.data.structure.searchtable.staticsearchtable.impl;

import com.data.structure.searchtable.model.Element;

/**
 * staticSearchTable:
 * 静态顺序查找表
 *
 * @author sunchen
 * @date 2020/6/19 2:09 下午
 */
public class SequenceStaticSearchTableImpl extends AbstractStaticSearchTable<Element> {
    /**
     * 用于存储数据
     */
    Element[] array;
    /**
     * 查找表长度
     */
    int length;

    public SequenceStaticSearchTableImpl(Element[] data) {
        length = data.length;
        array = new Element[length + 1];
        array[0] = new Element();
        System.arraycopy(data, 0, array, 1, data.length);
    }

    /**
     * 根据关键字key查找元素
     *
     * @param key 查找的值
     * @return 返回key为key的关键字的下标
     */
    @Override
    public Element search(Object key) {
        array[0].setKey(key);
        int index;
        for (index = length; array[index].equalKey(key) != 0; index--) {
        }
        // 找不到抛出异常
        if (index == 0) {
            throw new RuntimeException("没有找到" + key);
        }
        array[index].searchTime++;
        Element element = array[index];
        int orderIndex;
        int searchTime = array[index].searchTime;
        for (orderIndex = index; orderIndex < length; orderIndex++) {
            if (searchTime > array[orderIndex + 1].searchTime) {
                array[orderIndex] = array[orderIndex + 1];
            } else {
                break;
            }
        }
        array[orderIndex] = element;
        return element;
    }

    @Override
    public void traverse() {
        clear();
        for (int i = 1; i <= length; i++) {
            visit(array[i]);
        }
    }
}
