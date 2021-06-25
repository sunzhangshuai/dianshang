package com.data.structure.searchtable.dynamicsearchtable.impl;

import com.data.structure.searchtable.model.Element;
import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.tree.enums.BalanceFactor;
import com.data.structure.tree.impl.binarytree.BalanceBinarySortTreeImpl;
import com.data.structure.tree.model.binarytreenode.BalanceTreeNode;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

import java.util.List;

/**
 * BalanceBinarySortTreeSearchTableImpl:
 * 平衡二叉排序树(栈式插入)
 *
 * @author sunchen
 * @date 2020/6/28 10:42 上午
 */
public class BalanceBinarySortTreeStackSearchTableImpl extends BinarySortTreeSearchTableImpl {
    /**
     * 平衡树
     */
    public BalanceBinarySortTreeImpl tree = new BalanceBinarySortTreeImpl();

    @Override
    public Element search(Object key) {
        tree.stack.clearStack();
        return searchByNode(tree.node, key);
    }

    public Element searchByNode(BalanceTreeNode node, Object key) {
        if (node == null) {
            return null;
        }
        //首先判断是否和根相同
        if (((Element)node.data).equalKey(key) == 0) {
            return (Element) node.data;
        }
        if (((Element)node.data).equalKey(key) > 0) {
            //记录寻找过程中经过的父节点
            tree.stack.push(node);
            return searchByNode(node.lChild, key);
        } else {
            tree.stack.push(node);
            return searchByNode(node.rChild, key);
        }
    }

    @Override
    public boolean insert(Element e) {
        //1. 找结点，如果找到了，插入结束
        if (search(e.getKey()) != null) {
            tree.stack.clearStack();
            return false;
        }
        //2. 插入结点
        //创建要插入的结点
        BalanceTreeNode newNode = new BalanceTreeNode(e);
        if (tree.node == null) {
            tree.setRoot(newNode);
        } else {
            BalanceTreeNode parent = (BalanceTreeNode) tree.stack.getTop();
            int childType = ((Element)(parent.data)).equalKey((e.getKey())) > 0 ? 0 : 1;
            tree.insertChild(parent, childType, newNode);
        }
        tree.stack.clearStack();
        return true;
    }

    /**
     * 平衡二叉树的删除
     * @param key
     * @return
     */
    @Override
    public boolean delete(Object key) {
        //1. 先找结点
        Element search = search(key);
        if (search == null) {
            tree.stack.clearStack();
            return false;
        }
        if (tree.stack.stackEmpty()) {
            tree.deleteRoot(tree.node);
        } else {
            BalanceTreeNode parent = (BalanceTreeNode) tree.stack.getTop();
            int childType = ((Element)(parent.data)).equalKey((key)) > 0 ? 0 : 1;
            tree.deleteChild(parent, childType);
        }
        tree.stack.clearStack();
        return true;
    }

    @Override
    public void traverse() {
        values.clear();
        tree.inOrderTraverse();
        List<Object> datas = tree.datas;
        for (Object data : datas) {
            visit((Element) data);
        }
    }
}
