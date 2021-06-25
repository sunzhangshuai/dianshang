package com.data.structure.tree.impl.tree;

import com.data.structure.tree.model.treenode.ParentTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * ParentTreeImpl:
 *
 * @author sunchen
 * @date 2020/6/29 5:33 下午
 */
public class ParentTreeImpl extends AbstractTree<ParentTreeNode> {

    public Integer maxSize = 100;

    /**
     * 树结点数组
     */
    ParentTreeNode[] nodes;

    ParentTreeNode[] newNodes;

    int index;

    /**
     * 根的位置
     */
    int rootIndex;

    /**
     * 已使用的结点数
     */
    int n;

    public ParentTreeImpl(ParentTreeNode[] datas) {
        rootIndex = 0;
        n = datas.length;
        nodes = new ParentTreeNode[maxSize];
        System.arraycopy(datas, 0, nodes, 0, datas.length);
    }

    @Override
    public void clearTree() {
        for (int i = 0; i < n; i++) {
            nodes[i] = null;
        }
        rootIndex = -1;
        n = 0;
    }

    @Override
    public boolean isEmpty() {
        return rootIndex == -1;
    }

    @Override
    public int treeDepth() {
        return depth(rootIndex);
    }

    public int depth(int index) {
        List<Integer> children = getChildren(index);
        if (children.size() == 0) {
            return 1;
        }
        int max = 0;
        for (int child : children) {
            int depth = depth(child);
            if (depth > max) {
                max = depth;
            }
        }
        return max + 1;
    }

    @Override
    public ParentTreeNode getRoot() {
        if (rootIndex == -1) {
            return null;
        }
        return nodes[rootIndex];
    }

    @Override
    public ParentTreeNode parent(ParentTreeNode e) {
        if (e.parent == -1) {
            throw new RuntimeException("获取父亲结点出错" + e.data);
        }
        return nodes[e.parent];
    }

    @Override
    public ParentTreeNode leftChild(ParentTreeNode e) {
        List<Integer> children = getChildren(locate(e));
        if (children.size() >= 1) {
            return nodes[children.get(0)];
        }
        return null;
    }

    @Override
    public ParentTreeNode rightSibling(ParentTreeNode e) {
        if (e.parent == -1) {
            throw new RuntimeException("没有右兄弟");
        }
        List<Integer> children = getChildren(e.parent);
        int index = locate(e);
        for (int i = 0; i < children.size() - 1; i++) {
            if (children.get(i) == index) {
                return nodes[children.get(i + 1)];
            }
        }
        return null;
    }

    @Override
    public void insertChild(ParentTreeNode node, int i, ParentTreeNode e) {
        //找到第i个孩子，然后顺延后面的孩子
        if (n == maxSize) {
            //重新整理数组
            gc();
            if (n == maxSize) {
                throw new RuntimeException("树满了");
            }
        }

        int index = locate(node);
        List<Integer> children = getChildren(locate(node));
        if (i > children.size() + 1 || i < 1) {
            throw new RuntimeException("i不合法");
        }
        int pre = n;
        for (int j = children.size() - 1; j >= i; j--) {
            //把他们的孩子的父亲位置也做一下修改
            List<Integer> grandChildren = getChildren(j);
            for (Integer grandChildIndex : grandChildren) {
                nodes[grandChildIndex].parent = pre;
            }
            nodes[pre] = nodes[children.get(j)];
            pre = children.get(j);
        }
        e.parent = index;
        nodes[children.get(i)] = e;
        n++;
    }

    /**
     * 垃圾清除
     */
    private void gc() {
        if (isEmpty()) {
            clearTree();
            return;
        }
        newNodes = new ParentTreeNode[maxSize];
        transfer(rootIndex);
        nodes = newNodes;
        n = index;
        index = 0;
        newNodes = null;
    }

    public void transfer(int index) {
        addToNewArray(nodes[index]);
        List<Integer> childs = getChildren(index);
        for (Integer childIndex : childs) {
            transfer(childIndex);
        }
    }

    private void addToNewArray(ParentTreeNode oldNode) {
        ParentTreeNode node = new ParentTreeNode(oldNode.data, oldNode.parent);
        newNodes[index++] = node;
    }

    @Override
    public ParentTreeNode deleteChild(ParentTreeNode e, int i) {
        List<Integer> children = getChildren(locate(e));
        nodes[children.get(i - 1)].parent = -1;
        return nodes[children.get(i - 1)];
    }

    /**
     * 先根后孩子
     */
    @Override
    public void preOrderTraverse() {
        datas.clear();
        pre(rootIndex);
    }

    public void pre(int index) {
        visit(index);
        List<Integer> childs = getChildren(index);
        for (Integer childIndex : childs) {
            pre(childIndex);
        }
    }

    /**
     * 先孩子后根
     */
    @Override
    public void postOrderTraverse() {
        datas.clear();
        post(rootIndex);
    }

    public void post(int index) {
        List<Integer> children = getChildren(index);
        for (Integer childIndex : children) {
            post(childIndex);
        }
        visit(index);
    }

    public void visit(int index) {
        datas.add(nodes[index].data);
    }

    /**
     * 根据index查找孩子
     *
     * @param index
     * @return
     */
    public List<Integer> getChildren(int index) {
        List<Integer> childIndex = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].parent == index) {
                childIndex.add(i);
            }
        }
        return childIndex;
    }

    /**
     * 获取结点位置
     *
     * @param e
     * @return
     */
    public int locate(ParentTreeNode e) {
        for (int i = 0; i < n; i++) {
            if (nodes[i] == e) {
                return i;
            }
        }
        throw new RuntimeException("没有找到" + e.data);
    }
}
