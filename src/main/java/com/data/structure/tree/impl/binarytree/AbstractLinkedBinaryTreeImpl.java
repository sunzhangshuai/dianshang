package com.data.structure.tree.impl.binarytree;

import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * BinaryTreeImpl:
 * 链式二叉树
 *
 * @author sunchen
 * @date 2020/6/5 3:13 下午
 */
public abstract class AbstractLinkedBinaryTreeImpl extends AbstractBinaryTree<BinaryTreeNode> {

    @Override
    public void clearBinaryTree() {
        node = null;
    }

    @Override
    public int binaryTreeDepth() {
        return depth(node);
    }

    public int depth(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.lChild), depth(node.rChild)) + 1;
    }

    /**
     * 求父亲结点
     *
     * @param e
     * @return
     */
    @Override
    abstract public BinaryTreeNode parent(BinaryTreeNode e);

    @Override
    public BinaryTreeNode leftChild(BinaryTreeNode e) {
        if (e == null) {
            throw new RuntimeException("没有左孩子");
        }
        return e.lChild;
    }

    @Override
    public BinaryTreeNode rightChild(BinaryTreeNode e) {
        if (e == null) {
            throw new RuntimeException("没有右孩子");
        }
        return e.rChild;
    }

    @Override
    public BinaryTreeNode getRoot() {
        return node;
    }

    @Override
    public BinaryTreeNode leftSibling(BinaryTreeNode e) {
        BinaryTreeNode parent = parent(e);
        if (parent.lChild == e) {
            throw new RuntimeException("没有左兄弟:" + e.data);
        }
        return parent.lChild;
    }

    @Override
    public BinaryTreeNode rightSibling(BinaryTreeNode e) {
        BinaryTreeNode parent = parent(e);
        if (parent.rChild == e) {
            throw new RuntimeException("没有左兄弟:" + e.data);
        }
        return parent.rChild;
    }

    /**
     * 插入一个节点
     * @param binaryTreeNode
     * @param childType
     * @param e
     */
    @Override
    abstract public void insertChild(BinaryTreeNode binaryTreeNode, int childType, BinaryTreeNode e);
    /**
     * 删除结点
     *
     * @param binaryTreeNode 要删除的结点
     * @param childType      删除类型
     * @return
     */
    @Override
    abstract public BinaryTreeNode deleteChild(BinaryTreeNode binaryTreeNode, int childType);

    /**
     * 先序遍历
     */
    @Override
    abstract public void preOrderTraverse();

    /**
     * 中序遍历
     */
    @Override
    abstract public void inOrderTraverse();

    /**
     * 后序遍历
     */
    @Override
    abstract public void postOrderTraverse();

    /**
     * 层序遍历
     */
    @Override
    abstract public void levelOrderTraverse();
}
