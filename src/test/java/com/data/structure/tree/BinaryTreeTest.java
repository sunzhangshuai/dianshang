package com.data.structure.tree;

import com.data.structure.tree.impl.binarytree.BinaryTreeImpl;
import com.data.structure.tree.impl.op.StackTraverseBinaryTreeExtends;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * BinaryTreeTest:
 * 1
 * 2 0
 * 4 6
 * 0 0 0 0
 *
 * @author sunchen
 * @date 2020/6/22 4:28 下午
 */
public class BinaryTreeTest {
    StackTraverseBinaryTreeExtends binaryTree;

    @Before
    public void init() {
        int[] a = new int[]{1,4,4,0,0,6,2,0,0,0,8,9,0,1,0,0,0};
        binaryTree = new StackTraverseBinaryTreeExtends(a);
    }

    @Test
    public void depth() {
        int depth = binaryTree.binaryTreeDepth();
        Assert.assertEquals(3, depth);
    }

    @Test
    public void otherTest() {
        BinaryTreeNode root = binaryTree.getRoot();
        //左孩子
        BinaryTreeNode treeNode = binaryTree.leftChild(root);
        Assert.assertEquals(2, treeNode.data);
        //右孩子
        BinaryTreeNode treeNode1 = binaryTree.rightChild(treeNode);
        Assert.assertEquals(6, treeNode1.data);
        //父亲
        BinaryTreeNode parent = binaryTree.parent(treeNode1);
        Assert.assertEquals(2, parent.data);
        //左兄弟
        BinaryTreeNode treeNode2 = binaryTree.leftSibling(treeNode1);
        Assert.assertEquals(4, treeNode2.data);
        //右兄弟
        BinaryTreeNode treeNode3 = binaryTree.rightSibling(treeNode2);
        Assert.assertEquals(6, treeNode3.data);
    }

    @Test
    public void insert () {
        BinaryTreeNode node = new BinaryTreeNode("5");
        binaryTree.insertChild(binaryTree.getRoot().lChild, 0, node);
        binaryTree.preOrderTraverse();
        Object[] b = toArray(binaryTree.datas);
        Assert.assertArrayEquals(new Object[]{1,2,5,6,4},b);
    }

    @Test
    public void delete () {
        binaryTree.deleteChild(binaryTree.getRoot().lChild, 0);
        binaryTree.preOrderTraverse();
        Object[] b = toArray(binaryTree.datas);
        Assert.assertArrayEquals(new Object[]{1,2,6},b);
    }
    @Test
    public void getWPL(){
        int result = binaryTree.getWpl();
        System.out.println(result);
    }

    public Object[] toArray(List<Object> datas) {
        Object[] b = new Object[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            b[i] = datas.get(i);
        }
        return b;
    }
}
