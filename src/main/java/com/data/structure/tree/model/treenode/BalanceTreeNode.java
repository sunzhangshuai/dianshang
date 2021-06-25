package com.data.structure.tree.model.treenode;

import com.data.structure.searchtable.model.Element;

/**
 * Btree:
 *
 * @author sunchen
 * @date 2020/7/2 4:22 下午
 */
public class BalanceTreeNode {

    /**
     * 关键字个数
     */
    public int keyNum;

    /**
     * 指向双亲结点
     */
    public BalanceTreeNode parent;

    /**
     * 关键字向量，0号单元未用
     */
    public int[] key;

    /**
     * 子树指针向量
     */
    public BalanceTreeNode[] ptr;

    /**
     * 记录指针向量，0号单元未用
     */
    public Element[] recPtr;


}
