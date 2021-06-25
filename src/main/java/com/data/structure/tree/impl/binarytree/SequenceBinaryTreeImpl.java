package com.data.structure.tree.impl.binarytree;

import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * SequenceBinaryTreeImpl:
 * 顺序存储二叉树
 *
 * @author sunchen
 * @date 2020/6/19 5:01 下午
 */
public class SequenceBinaryTreeImpl extends AbstractBinaryTree<BinaryTreeNode> {
    /**
     * 树的最大容量
     */
    int maxTreeSize = 100;
    /**
     * 用于存储结点
     */
    BinaryTreeNode[] nodes;

    public SequenceBinaryTreeImpl(int[] datas) {
        nodes = new BinaryTreeNode[maxTreeSize];
        for (int i = 0; i < datas.length; i++) {
            nodes[i] = new BinaryTreeNode(datas[i]);
        }
    }

    @Override
    public void clearBinaryTree() {
        nodes[0] = null;
    }

    /**
     * 计算n个结点的深度
     *
     * @return
     */
    @Override
    public int binaryTreeDepth() {
        return getDepthByLocate(0);
    }

    public int getDepthByLocate(int index) {
        if (nodes[index] == null && index >= maxTreeSize) {
            return 0;
        }
        int leftDepth = getDepthByLocate(getLeftChildIndex(index));
        int rightDepth = getDepthByLocate(getRightChildIndex(index));
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public BinaryTreeNode getRoot() {
        return nodes[0];
    }

    @Override
    public BinaryTreeNode parent(BinaryTreeNode e) {
        int index = locate(e);
        if (index == 0) {
            throw new RuntimeException("没有双亲");
        }
        return nodes[(index - 1) / 2];
    }

    @Override
    public BinaryTreeNode leftChild(BinaryTreeNode e) {
        int index = locate(e);
        int left = getLeftChildIndex(index);
        if (left >= maxTreeSize) {
            return null;
        }
        return nodes[left];
    }

    @Override
    public BinaryTreeNode rightChild(BinaryTreeNode e) {
        int index = locate(e);
        int right = getRightChildIndex(index);
        if (right >= maxTreeSize) {
            return null;
        }
        return nodes[right];
    }

    @Override
    public BinaryTreeNode leftSibling(BinaryTreeNode e) {
        int index = locate(e);
        int div = 2;
        if (index == 0 || index % div != 0) {
            throw new RuntimeException("没有左兄弟");
        }
        return nodes[index - 1];
    }

    @Override
    public BinaryTreeNode rightSibling(BinaryTreeNode e) {
        int index = locate(e);
        int div = 2;
        if (index % div == 0) {
            throw new RuntimeException("没有右兄弟");
        }
        return nodes[index + 1];
    }

    @Override
    public void insertChild(BinaryTreeNode binaryTreeNode, int childType, BinaryTreeNode e) {
        //为结点设置左孩子
        int index = locate(binaryTreeNode);
        if (childType == 0) {
            nodes[(index + 1) * 2 - 1] = e;
        } else {
            //为结点设置右孩子
            nodes[(index + 1) * 2] = e;
        }
    }

    @Override
    public BinaryTreeNode deleteChild(BinaryTreeNode binaryTreeNode, int childType) {
        int index = locate(binaryTreeNode);
        int childIndex = childType == 0 ? getLeftChildIndex(index) : getRightChildIndex(index);
        if (index >= maxTreeSize) {
            return null;
        }
        BinaryTreeNode node = nodes[childIndex];
        nodes[childIndex] = null;
        return node;
    }

    @Override
    public void preOrderTraverse() {
        pre(0);
    }

    public void pre(int index) {
        if (nodes[index] == null || index >= maxTreeSize) {
            return;
        }
        visit(nodes[index].data);
        pre(getLeftChildIndex(index));
        pre(getRightChildIndex(index));
    }

    @Override
    public void inOrderTraverse() {
        in(0);
    }

    public void in(int index) {
        if (nodes[index] == null || index >= maxTreeSize) {
            return;
        }
        in(getLeftChildIndex(index));
        visit(nodes[index].data);
        in(getRightChildIndex(index));
    }

    @Override
    public void postOrderTraverse() {
        post(0);
    }

    public void post(int index) {
        if (nodes[index] == null || index >= maxTreeSize) {
            return;
        }
        post(getLeftChildIndex(index));
        post(getRightChildIndex(index));
        visit(nodes[index].data);
    }

    @Override
    public void levelOrderTraverse() {
        if (isEmpty()) {
            return;
        }
        queue.clearQueue();
        queue.enQueue(0);
        while (!queue.queueEmpty()) {
            int index = (Integer) queue.deQueue();
            visit(nodes[index].data);
            int left = getLeftChildIndex(index);
            if (nodes[left] != null && left < maxTreeSize) {
                queue.enQueue(left);
            }
            int right = getRightChildIndex(index);
            if (nodes[right] != null && right < maxTreeSize) {
                queue.enQueue(right);
            }
        }
    }

    public int locate(BinaryTreeNode node) {
        for (int i = 0; i < maxTreeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        throw new RuntimeException("没有找到" + node.data);
    }

    private int getLeftChildIndex(int index) {
        return (index + 1) * 2 - 1;
    }

    private int getRightChildIndex(int index) {
        return (index + 1) * 2;
    }
}
