package com.data.structure.tree.model.binarytreenode;

/**
 * AbstractTreeNode:
 *
 * @author sunchen
 * @date 2020/6/8 12:24 上午
 */
public class AbstractTreeNode<T> {
    /**
     * 数据域
     */
    public Object data;

    /**
     * 左结点
     */
    public T lChild;

    /**
     * 右结点
     */
    public T rChild;

    public AbstractTreeNode(Object data) {
        this.data = data;
    }
}
