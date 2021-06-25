package com.data.structure.tree.model.treenode;

/**
 * TreeNode:
 * 双亲表示法
 *
 * @author sunchen
 * @date 2020/6/29 5:23 下午
 */
public class ParentTreeNode {
    public int parent;
    public Object data;

    public ParentTreeNode (Object data) {
        this.data = data;
    }

    public ParentTreeNode (Object data, int parent) {
        this.data = data;
        this.parent = parent;
    }
}
