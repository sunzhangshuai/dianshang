package com.data.structure.tree.impl.tree;

import com.data.structure.tree.model.treenode.BalanceTreeNode;

/**
 * BTreeImpl:
 * b-树
 *
 * @author sunchen
 * @date 2020/7/2 4:22 下午
 */
public class BtreeImpl extends AbstractTree<BalanceTreeNode>{

    public BalanceTreeNode node;

    @Override
    public void clearTree() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int treeDepth() {
        return 0;
    }

    @Override
    public BalanceTreeNode getRoot() {
        return null;
    }

    @Override
    public BalanceTreeNode parent(BalanceTreeNode e) {
        return null;
    }

    @Override
    public BalanceTreeNode leftChild(BalanceTreeNode e) {
        return null;
    }

    @Override
    public void insertChild(BalanceTreeNode balanceTreeNode, int i, BalanceTreeNode e) {

    }

    @Override
    public BalanceTreeNode deleteChild(BalanceTreeNode balanceTreeNode, int i) {
        return null;
    }

    @Override
    public BalanceTreeNode rightSibling(BalanceTreeNode e) {
        return null;
    }

    @Override
    public void preOrderTraverse() {

    }

    @Override
    public void postOrderTraverse() {

    }
}
