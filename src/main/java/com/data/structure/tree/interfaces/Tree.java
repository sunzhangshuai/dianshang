package com.data.structure.tree.interfaces;

/**
 * @author sunchen
 */
public interface Tree<T> {
    /**
     * 清空二叉树
     */
    void clearTree();

    /**
     * 二叉树是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 二叉树的高度
     *
     * @return 高度
     */
    int treeDepth();

    /**
     * 返回二叉树的根
     *
     * @return
     */
    T getRoot();

    /**
     * 返回结点t的值
     *
     * @param t 结点t
     * @return
     */
    Object value(T t);

    /**
     * 为结点t的data赋值
     *
     * @param t
     * @param value
     */
    void assign(T t, Object value);

    /**
     * 求e的双亲，如果e是根结点，则返回null
     *
     * @param e
     * @return
     */
    T parent(T e);

    /**
     * 返回e的左孩子，如果没有左孩子，则返回null
     *
     * @param e
     * @return
     */
    T leftChild(T e);

    /**
     * 插入e为t结点的第i棵子树
     *
     * @param t
     * @param i
     * @param e
     */
    void insertChild(T t, int i, T e);

    /**
     * 删除t的第i棵子树
     *
     * @param t
     * @param i
     * @return
     */
    T deleteChild(T t, int i);

    /**
     * 返回e的右兄弟，若e是根结点的右孩子，或者没有右兄弟，则返回null
     *
     * @param e
     * @return
     */
    T rightSibling(T e);

    /**
     * 先根遍历
     */
    void preOrderTraverse();

    /**
     * 后根遍历
     */
    void postOrderTraverse();

}
