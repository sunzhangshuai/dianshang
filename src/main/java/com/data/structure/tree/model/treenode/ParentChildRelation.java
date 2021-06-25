package com.data.structure.tree.model.treenode;

/**
 * ParentChildRelation:
 *
 * @author sunchen
 * @date 2020/6/30 5:12 下午
 */
public class ParentChildRelation {
    public Object parent;
    public Object child;

    public ParentChildRelation(Object parent, Object child) {
        this.parent = parent;
        this.child = child;
    }
}
