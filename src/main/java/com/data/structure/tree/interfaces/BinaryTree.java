package com.data.structure.tree.interfaces;

/**
 * BinaryTree:
 *
 * @author sunchen
 * @date 2020/6/5 3:13 下午
 */
public interface BinaryTree<T> {
    /**
     * 清空二叉树
     */
    void clearBinaryTree();

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
    int binaryTreeDepth();

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
     * 返回e的右孩子，如果没有右孩子，则返回null
     *
     * @param e
     * @return
     */
    T rightChild(T e);

    /**
     * 返回e的左兄弟，若e是根结点的左孩子，或者没有左兄弟，则返回null
     *
     * @param e
     * @return
     */
    T leftSibling(T e);

    /**
     * 返回e的右兄弟，若e是根结点的右孩子，或者没有右兄弟，则返回null
     *
     * @param e
     * @return
     */
    T rightSibling(T e);

    /**
     * 添加一棵树
     *
     * @param t
     * @param childType
     * @param e
     */
    void insertChild(T t, int childType, T e);

    /**
     * 删除一棵树
     *
     * @param t
     * @param childType
     * @return
     */
    T deleteChild(T t, int childType);


    /**
     * 先序遍历
     */
    void preOrderTraverse();

    /**
     * 中序遍历
     */
    void inOrderTraverse();

    /**
     * 后序遍历
     */
    void postOrderTraverse();

    /**
     * 层序遍历
     */
    void levelOrderTraverse();
}
