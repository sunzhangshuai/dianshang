package com.data.structure.tree.model.treenode;

/**
 * ChildTreeNode:
 * <p>
 * 孩子结点
 *
 * @author sunchen
 * @date 2020/6/30 4:44 下午
 */
public class ChildTreeNode {

    /**
     * 结点在结点数组的下标
     */
    public int child;

    /**
     * 兄弟结点（其父亲的下一个孩子结点）
     */
    public ChildTreeNode next;

    public ChildTreeNode(int child) {
        this.child = child;
    }
}
