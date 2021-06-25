package com.data.structure.generalizedlist.model;

/**
 * GeneralizedNode:
 *
 * @author sunchen
 * @date 2020/5/24 11:23 上午
 */
public class GeneralizedNode<T> {
    /**
     * 标识 0-原子结点 1-子表
     */
    public int tag;
    /**
     * 值域
     */
    public Object atom;
    /**
     * 指向表头
     */
    public T hp;
    /**
     * 指向表尾
     */
    public T tp;

}
