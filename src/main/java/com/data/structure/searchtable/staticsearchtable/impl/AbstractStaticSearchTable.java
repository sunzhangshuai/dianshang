package com.data.structure.searchtable.staticsearchtable.impl;

import com.data.structure.searchtable.interfaces.StaticSearchTable;
import com.data.structure.searchtable.model.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractStaticSearchTable:
 *
 * @author sunchen
 * @date 2020/6/19 2:31 下午
 */
public abstract class AbstractStaticSearchTable<T> implements StaticSearchTable<T> {
    /**
     * 用于存储数据
     */
    Element[] elementData;
    /**
     * 查找表长度
     */
    int length;

    public List<String> data = new ArrayList<String>();
    /**
     * 根据关键字key查找元素
     * @param key
     * @return
     */
    @Override
    abstract public T search(Object key);

    /**
     * 遍历
     */
    @Override
    public void traverse() {
        clear();
        for (int i = 1; i <= length; i++) {
            visit(elementData[i]);
        }
    }

    public void visit(Element element) {
        data.add(element.value);
    }

    public void clear() {
        data = new ArrayList<String>();
    }

    public void sort () {
        //1.将数组排序
        for (int i = 2; i <= length; i++) {
            Element temp = elementData[i];
            int j;
            for (j = i; j > 1; j--) {
                if (elementData[j - 1].strKey.compareTo(temp.strKey) > 0) {
                    elementData[j] = elementData[j - 1];
                } else {
                    break;
                }
            }
            elementData[j] = temp;
        }
    }
}
