package com.data.structure.tree;

import com.data.structure.tree.impl.tree.ChildParentTreeImpl;
import com.data.structure.tree.model.treenode.ChildParentTreeBox;
import com.data.structure.tree.model.treenode.ParentChildRelation;
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
public class ChildParentTreeTest {

    ChildParentTreeImpl childParentTree;

    @Before
    public void init() {
        ParentChildRelation[] datas = new ParentChildRelation[]{
                new ParentChildRelation("R", "C"),
                new ParentChildRelation("R", "B"),
                new ParentChildRelation("R", "A"),
                new ParentChildRelation("A", "E"),
                new ParentChildRelation("A", "D"),
                new ParentChildRelation("C", "F"),
                new ParentChildRelation("F", "K"),
                new ParentChildRelation("F", "H"),
                new ParentChildRelation("F", "G"),
        };
        Object[] data = new Object[]{
                "R",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "K"
        };
        childParentTree = new ChildParentTreeImpl(data, datas);
        childParentTree.preOrderTraverse();
        List<Object> datas1 = childParentTree.datas;
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
        childParentTree.postOrderTraverse();
        List<Object> datas2 = childParentTree.datas;
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
    public void depth() {
        int depth = childParentTree.treeDepth();
        Assert.assertEquals(depth, 4);
    }

    @Test
    public void otherTest() {
        ChildParentTreeBox root = childParentTree.getRoot();
        ChildParentTreeBox childParentTreeBox = childParentTree.leftChild(root);
        Assert.assertEquals("A", childParentTreeBox.data);
        ChildParentTreeBox node = childParentTree.rightSibling(childParentTreeBox);
        Assert.assertEquals("B", node.data);
        ChildParentTreeBox treeNode = new ChildParentTreeBox(1, "M");
        childParentTree.insertChild(root, 2, treeNode);
        childParentTree.preOrderTraverse();
        List<Object> datas1 = childParentTree.datas;
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

        childParentTree.deleteChild(childParentTreeBox, 2);
        childParentTree.preOrderTraverse();
        datas1 = childParentTree.datas;
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
