package com.data.structure.tree.model.treenode;

/**
 * ChildTreeBox:
 * 多个孩子链表的头指针组成的数组
 *
 * @author sunchen
 * @date 2020/6/30 4:46 下午
 */
public class ChildParentTreeBox {

    /**
     * 某一个结点的父亲下标
     */
    public int parentIndex;

    /**
     * 结点名称
     */
    public Object data;

    /**
     * 孩子链表头指针
     */
    public ChildTreeNode firstChild;

    public ChildParentTreeBox(int parentIndex, Object data) {
        this.parentIndex = parentIndex;
        this.data = data;
    }

    public ChildParentTreeBox(Object data) {
        this.data = data;
    }
}
