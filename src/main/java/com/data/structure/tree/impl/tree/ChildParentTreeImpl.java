package com.data.structure.tree.impl.tree;

import com.data.structure.tree.model.treenode.ChildParentTreeBox;
import com.data.structure.tree.model.treenode.ChildTreeNode;
import com.data.structure.tree.model.treenode.ParentChildRelation;

/**
 * ChildParentTreeImpl:
 * 孩子双亲表示法基本方法实现
 *
 * @author sunchen
 * @date 2020/6/30 4:53 下午
 */
public class ChildParentTreeImpl extends AbstractTree<ChildParentTreeBox> {

    private Integer MAX_TREE_SIZE = 100;

    ChildParentTreeBox[] nodes;

    int rootIndex;

    int n;

    public ChildParentTreeImpl(Object[] data, ParentChildRelation[] datas) {
        nodes = new ChildParentTreeBox[MAX_TREE_SIZE];
        rootIndex = 0;
        for (int i = 0; i < data.length; i++) {
            insertNode(data[i]);
        }
        for (int i = 0; i < datas.length; i++) {
            insertChild(datas[i]);
        }
    }




    @Override
    public void clearTree() {
        for (int i = 0; i < n; i++) {
            ChildTreeNode firstChild = nodes[i].firstChild;
            while (firstChild != null) {
                ChildTreeNode node = firstChild.next;
                firstChild.next = null;
                firstChild = node;
            }
        }
        nodes = null;
        rootIndex = n = 0;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int treeDepth() {
        return depth(rootIndex);
    }

    public int depth(int index) {
        ChildTreeNode childNode = nodes[index].firstChild;
        if (childNode == null) {
            return 1;
        }
        int max = 0;
        while (childNode != null) {
            int depth = depth(childNode.child);
            if (depth > max) {
                max = depth;
            }
            childNode = childNode.next;
        }
        return max + 1;
    }

    @Override
    public ChildParentTreeBox getRoot() {
        return nodes[rootIndex];
    }

    @Override
    public ChildParentTreeBox parent(ChildParentTreeBox e) {
        if (e.parentIndex == -1) {
            throw new RuntimeException(e.data + "没有父类");
        }
        return nodes[e.parentIndex];
    }

    @Override
    public ChildParentTreeBox leftChild(ChildParentTreeBox e) {
        return nodes[e.firstChild.child];
    }

    @Override
    public ChildParentTreeBox rightSibling(ChildParentTreeBox e) {
        for (int i = 0; i < n; i++) {
            ChildTreeNode childTreeNode = nodes[i].firstChild;
            while (childTreeNode != null && childTreeNode.next != null) {
                if (nodes[childTreeNode.child].data.equals(e.data)) {
                    return nodes[childTreeNode.next.child];
                }
                childTreeNode = childTreeNode.next;
            }
        }
        return null;
    }


    @Override
    public void insertChild(ChildParentTreeBox childParentTreeBox, int i, ChildParentTreeBox e) {
        //相当于邻接表插入一个弧关系
        int parentIndex = locate(childParentTreeBox.data);
        e.parentIndex = parentIndex;
        nodes[n] = e;
        //找到孩子链表中要插入的位置
        ChildTreeNode firstChild = nodes[parentIndex].firstChild;
        ChildTreeNode childTreeNode = new ChildTreeNode(n);
        if (firstChild == null || i == 1) {
            childTreeNode.next = firstChild;
            nodes[parentIndex].firstChild = childTreeNode;
            return;
        }
        int count = 1;
        while (firstChild.next != null) {
            count++;
            if (count == i) {
                break;
            }
            firstChild = firstChild.next;
        }
        childTreeNode.next = firstChild.next;
        firstChild.next = childTreeNode;
    }

    @Override
    public ChildParentTreeBox deleteChild(ChildParentTreeBox childParentTreeBox, int i) {
        int parentIndex = locate(childParentTreeBox.data);
        ChildParentTreeBox deleteNode;
        ChildTreeNode child = nodes[parentIndex].firstChild;
        if (i == 1) {
            deleteNode = nodes[child.child];
            nodes[parentIndex].firstChild = child.next;
            return deleteNode;
        }
        int count = 2;
        while (child.next != null) {
            if (i == count) {
                break;
            }
            child = child.next;
        }
        deleteNode = nodes[child.next.child];
        child.next = child.next.next;
        return deleteNode;
    }

    @Override
    public void preOrderTraverse() {
        datas.clear();
        pre(rootIndex);
    }

    public void pre(int index) {
        visit(nodes[index].data);
        ChildTreeNode firstChild = nodes[index].firstChild;
        while (firstChild != null) {
            pre(firstChild.child);
            firstChild = firstChild.next;
        }
    }

    @Override
    public void postOrderTraverse() {
        datas.clear();
        in(rootIndex);
    }

    public void in(int index) {
        ChildTreeNode firstChild = nodes[index].firstChild;
        while (firstChild != null) {
            in(firstChild.child);
            firstChild = firstChild.next;
        }
        visit(nodes[index].data);
    }

    public void visit(Object data) {
        datas.add(data);
    }

    /**
     * 求值为data的结点的位置
     *
     * @param data
     * @return
     */
    public int locate(Object data) {
        for (int i = 0; i < n; i++) {
            if (nodes[i].data.equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public void insertNode(Object data) {
        ChildParentTreeBox box = new ChildParentTreeBox(data);
        nodes[n] = box;
        n++;
    }

    public void insertChild(ParentChildRelation data) {
        //获取父亲结点的下标
        int parentIndex = locate(data.parent);
        //获取结点的下标
        int childIndex = locate(data.child);
        //为结点添加父信息
        nodes[childIndex].parentIndex = parentIndex;
        //新增孩子结点
        ChildTreeNode newNode = new ChildTreeNode(childIndex);
        newNode.next = nodes[parentIndex].firstChild;
        nodes[parentIndex].firstChild = newNode;
    }
}
