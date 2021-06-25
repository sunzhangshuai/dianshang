package com.data.structure.tree.impl.binarytree;

import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * BinaryTreeImpl:
 * 二叉树排序树
 *j
 * @author sunchen
 * @date 2020/6/5 3:13 下午
 */
public class BinarySortTreeImpl extends BinaryTreeImpl {

    /**
     * 删除节点的方法
     */
    public int deleteMethod = 3;

    public BinarySortTreeImpl() {}

    public void setRoot (BinaryTreeNode node) {
        this.node = node;
    }

    @Override
    public void insertChild(BinaryTreeNode binaryTreeNode, int childType, BinaryTreeNode e) {
        if (childType == 0){
            binaryTreeNode.lChild = e;
        } else {
            binaryTreeNode.rChild = e;
        }
    }

    @Override
    public BinaryTreeNode deleteChild(BinaryTreeNode binaryTreeNode, int childType) {
        BinaryTreeNode node = childType == 0 ? binaryTreeNode.lChild:binaryTreeNode.rChild;
        if (childType == 0) {
            binaryTreeNode.lChild = delete(node);
        } else {
            binaryTreeNode.rChild = delete(node);
        }
        return node;
    }

    public void deleteRoot(BinaryTreeNode root){
        this.node = delete(root);
    }

    public BinaryTreeNode delete (BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.lChild == null) {
            return node.rChild;
        }
        if (node.rChild == null) {
            return node.lChild;
        }
        switch (deleteMethod) {
            case 1:
                return delete1(node);
            case 2:
                return delete2(node);
            case 3:
                return delete3(node);
            default:
                return null;
        }
    }

    /**
     *
     * @param node
     * @return
     */
    public BinaryTreeNode delete1 (BinaryTreeNode node) {
        BinaryTreeNode p = node.lChild;
        //寻找node的前驱
        while (p.rChild != null) {
            p = p.rChild;
        }
        p.rChild = node.rChild;
        return node.lChild;
    }

    /**
     *
     * @param node
     * @return
     */
    public BinaryTreeNode delete2 (BinaryTreeNode node) {
        BinaryTreeNode pre = node.lChild;
        BinaryTreeNode preParent = node;
        while (pre.rChild != null) {
            preParent = pre;
            pre = pre.rChild;
        }
        node.data = pre.data;
        if (preParent == node) {
            node.lChild = pre.lChild;
        } else {
            preParent.rChild = pre.lChild;
        }
        return node;
    }

    /**
     *
     * @param node
     * @return
     */
    public BinaryTreeNode delete3 (BinaryTreeNode node) {
        BinaryTreeNode next = node.rChild;
        BinaryTreeNode nextParent = node;
        while (next.lChild != null) {
            nextParent = next;
            next = next.lChild;
        }
        node.data = next.data;
        if (nextParent == node) {
            nextParent.rChild = next.rChild;
        } else {
            nextParent.lChild = next.rChild;
        }
        return node;
    }
}
