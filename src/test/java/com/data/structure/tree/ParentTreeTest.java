package com.data.structure.tree;

import com.data.structure.tree.impl.tree.ParentTreeImpl;
import com.data.structure.tree.model.treenode.ParentTreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * ParentTreeTest:
 *
 * @author sunchen
 * @date 2020/6/30 10:04 上午
 */
public class ParentTreeTest {
    ParentTreeImpl parentTree;
    @Before
    public void init () {
        ParentTreeNode[] datas = new ParentTreeNode[]{
                new ParentTreeNode("R", -1),
                new ParentTreeNode("A", 0),
                new ParentTreeNode("B", 0),
                new ParentTreeNode("C", 0),
                new ParentTreeNode("D", 1),
                new ParentTreeNode("E", 1),
                new ParentTreeNode("F", 3),
                new ParentTreeNode("G", 6),
                new ParentTreeNode("H", 6),
                new ParentTreeNode("K", 6),
        };
        parentTree = new ParentTreeImpl(datas);
        parentTree.preOrderTraverse();
        List<Object> datas1 = parentTree.datas;
        List<Object> result1 = new ArrayList<Object>();
        result1.add("R");
        result1.add("A");
        result1.add("D");
        result1.add("E");
        result1.add("B");
        result1.add("C");
        result1.add("F");
        result1.add("G");
        result1.add("H");
        result1.add("K");
        Assert.assertEquals(result1, datas1);
        parentTree.postOrderTraverse();
        List<Object> datas2 = parentTree.datas;
        List<Object> result2 = new ArrayList<Object>();
        result2.add("D");
        result2.add("E");
        result2.add("A");
        result2.add("B");
        result2.add("G");
        result2.add("H");
        result2.add("K");
        result2.add("F");
        result2.add("C");
        result2.add("R");
        Assert.assertEquals(datas2, result2);
    }

    @Test
    public void depth () {
        int depth = parentTree.treeDepth();
        Assert.assertEquals(depth, 4);
    }

    @Test
    public void otherTest () {
        ParentTreeNode root = parentTree.getRoot();
        ParentTreeNode node = parentTree.leftChild(root);
        Assert.assertEquals("A", node.data);
        ParentTreeNode parentTreeNode = parentTree.rightSibling(node);
        Assert.assertEquals("B", parentTreeNode.data);
        ParentTreeNode treeNode = new ParentTreeNode("M", 0);
        parentTree.insertChild(root, 2, treeNode);
        parentTree.preOrderTraverse();
        List<Object> datas1 = parentTree.datas;
        List<Object> result1 = new ArrayList<Object>();
        result1.add("R");
        result1.add("A");
        result1.add("D");
        result1.add("E");
        result1.add("M");
        result1.add("B");
        result1.add("C");
        result1.add("F");
        result1.add("G");
        result1.add("H");
        result1.add("K");
        Assert.assertEquals(result1, datas1);

        parentTree.deleteChild(node, 2);
        parentTree.preOrderTraverse();
        datas1 = parentTree.datas;
        result1 = new ArrayList<Object>();
        result1.add("R");
        result1.add("A");
        result1.add("D");
        result1.add("M");
        result1.add("B");
        result1.add("C");
        result1.add("F");
        result1.add("G");
        result1.add("H");
        result1.add("K");
        Assert.assertEquals(result1, datas1);
    }
}
