package com.data.structure.searchtable.dynamicsearchtable.impl;

import com.data.structure.searchtable.interfaces.DynamicSearchTable;
import com.data.structure.searchtable.model.Element;
import com.data.structure.stack.impl.SequenceStackImpl;

/**
 * AbstractDynamicSearchTable:
 *
 * @author sunchen
 * @date 2020/6/26 3:23 下午
 */
public abstract class AbstractDynamicSearchTable implements DynamicSearchTable<Element> {
    /**
     * 查找
     * @param key
     * @return
     */
    @Override
    abstract public Element search(Object key);

    /**
     * 插入
     * @param e
     * @return
     */
    @Override
    abstract public boolean insert(Element e);

    /**
     * 删除
     * @param key
     * @return
     */
    @Override
    abstract public boolean delete(Object key);


    @Override
    public void traverse() {

    }
}
