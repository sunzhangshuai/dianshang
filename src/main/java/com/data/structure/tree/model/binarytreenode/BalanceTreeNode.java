package com.data.structure.tree.model.binarytreenode;

import com.data.structure.tree.enums.BalanceFactor;

/**
 * BalanceTreeNode:
 *
 * @author sunchen
 * @date 2020/6/28 11:06 上午
 */
public class BalanceTreeNode extends BinaryTreeNode{
    public BalanceFactor bf;
    /**
     * 左结点
     */
    public BalanceTreeNode lChild;

    /**
     * 右结点
     */
    public BalanceTreeNode rChild;

    public BalanceTreeNode(Object data) {
        super(data);
        bf = BalanceFactor.EH;
    }
}
