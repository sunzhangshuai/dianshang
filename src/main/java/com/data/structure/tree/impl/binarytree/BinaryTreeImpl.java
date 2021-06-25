package com.data.structure.tree.impl.binarytree;

import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * BinaryTreeImpl:
 * 链式二叉树
 *
 * @author sunchen
 * @date 2020/6/5 3:13 下午
 */
public class BinaryTreeImpl extends AbstractLinkedBinaryTreeImpl {

    int index = 0;

    public BinaryTreeImpl() {
    }

    public BinaryTreeImpl(int[] data) {
        node = createTree(data);
    }

    private BinaryTreeNode createTree(int[] data) {
        if (data[index] == 0) {
            index++;
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(data[index]);
        index++;
        node.lChild = createTree(data);
        node.rChild = createTree(data);
        return node;
    }

    @Override
    public BinaryTreeNode parent(BinaryTreeNode e) {
        if (node == null) {
            throw new RuntimeException("查询父节点时，子节点不能为空");
        } else {
            queue.enQueue(node);
        }
        while (!queue.queueEmpty()) {
            BinaryTreeNode treeNode = (BinaryTreeNode) queue.deQueue();
            if (treeNode.lChild != null && treeNode.lChild == e) {
                return treeNode;
            }
            if (treeNode.rChild != null && treeNode.rChild == e) {
                return treeNode;
            }
            if (treeNode.lChild != null) {
                queue.enQueue(treeNode.lChild);
            }

            if (treeNode.rChild != null) {
                queue.enQueue(treeNode.rChild);
            }
        }
        throw new RuntimeException("没有找到结点" + e.data + "的父结点");
    }

    @Override
    public void insertChild(BinaryTreeNode binaryTreeNode, int childType, BinaryTreeNode e) {
        // 插入一个结点
        if (childType == 0) {
            e.rChild = binaryTreeNode.lChild;
            binaryTreeNode.lChild = e;
        } else {
            e.rChild = binaryTreeNode.rChild;
            binaryTreeNode.rChild = e;
        }
    }

    @Override
    public BinaryTreeNode deleteChild(BinaryTreeNode binaryTreeNode, int childType) {
        BinaryTreeNode node = childType == 0 ? binaryTreeNode.lChild : binaryTreeNode.rChild;
        if (node == null) {
            return null;
        }
        // 1. 删除的是叶子节点
        if (node.lChild == null && node.rChild == null) {
            if (childType == 0) {
                binaryTreeNode.lChild = null;
            } else {
                binaryTreeNode.rChild = null;
            }
            return node;
        }
        // 2. 删除的节点有两个孩子
        if (node.rChild != null && node.lChild != null) {
            //找到最小的那个结点
            BinaryTreeNode findNode = node.rChild;
            BinaryTreeNode pre = node;
            if (findNode.lChild == null) {
                node.data = findNode.data;
                node.rChild = null;
                return node;
            }
            while (findNode.lChild != null) {
                pre = findNode;
                findNode = findNode.lChild;
            }
            pre.lChild = findNode.rChild;
            node.data = findNode.data;
            return node;
        }
        // 3. 删除的节点只有一个孩子
        BinaryTreeNode grandChild = node.lChild == null ? node.rChild : node.lChild;
        if (childType == 0) {
            binaryTreeNode.lChild = grandChild;
        } else {
            binaryTreeNode.rChild = grandChild;
        }
        return node;
    }

    @Override
    public void preOrderTraverse() {
        clear();
        pre(node);
    }

    public void pre(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        visit(node.data);
        pre(node.lChild);
        pre(node.rChild);
    }

    @Override
    public void inOrderTraverse() {
        clear();
        in(node);
    }

    public void in(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        in(node.lChild);
        visit(node.data);
        in(node.rChild);
    }

    @Override
    public void postOrderTraverse() {
        clear();
        post(node);
    }

    public void post(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        post(node.lChild);
        post(node.rChild);
        visit(node.data);
    }

    @Override
    public void levelOrderTraverse() {
        clear();
        if (node == null) {
            return;
        } else {
            queue.enQueue(node);
        }
        while (!queue.queueEmpty()) {
            BinaryTreeNode treeNode = (BinaryTreeNode) queue.deQueue();
            visit(treeNode.data);
            if (treeNode.lChild != null) {
                queue.enQueue(treeNode.lChild);
            }
            if (treeNode.rChild != null) {
                queue.enQueue(treeNode.rChild);
            }
        }
    }
}
