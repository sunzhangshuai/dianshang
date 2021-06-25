package com.data.structure.tree.impl.tree;

import com.data.structure.tree.interfaces.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractTree:
 *
 * @author sunchen
 * @date 2020/6/29 5:26 下午
 */
public abstract class AbstractTree<T> implements Tree<T> {
    /**
     * 用于存放遍历过程中遇到的结点
     */
    public List<Object> datas = new ArrayList<Object>();
    /**
     * 清空树
     */
    @Override
    public abstract void clearTree();

    /**
     * 是否是空树
     * @return
     */
    @Override
    public abstract boolean isEmpty();

    /**
     * 计算树的高度
     * @return
     */
    @Override
    public abstract int treeDepth();

    /**
     * 求根
     * @return
     */
    @Override
    public abstract T getRoot();

    @Override
    public Object value(T t) {
        return null;
    }

    @Override
    public void assign(T t, Object value) {

    }

    /**
     * 求双亲
     * @param e
     * @return
     */
    @Override
    public abstract T parent(T e);

    /**
     * 左孩子
     * @param e
     * @return
     */
    @Override
    public abstract T leftChild(T e);

    /**
     * 插入树
     * @param t
     * @param i
     * @param e
     */
    @Override
    public abstract void insertChild(T t, int i, T e);

    /**
     * 删除树
     * @param t
     * @param i
     * @return
     */
    @Override
    public abstract T deleteChild(T t, int i);

    /**
     * 获取右兄弟
     * @param e
     * @return
     */
    @Override
    public abstract T rightSibling(T e);

    /**
     * 树的先根遍历
     */
    @Override
    abstract public void preOrderTraverse();

    /**
     * 树的后根遍历
     */
    @Override
    abstract public void postOrderTraverse();
}
