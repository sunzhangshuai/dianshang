package com.data.structure.searchtable.dynamicsearchtable.impl;

import com.data.structure.searchtable.model.Element;
import com.data.structure.tree.impl.binarytree.BinarySortTreeImpl;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * BinarySortTreeImpl:
 * 二叉排序树查找
 *
 * @author sunchen
 * @date 2020/6/26 3:19 下午
 */
public class BinarySortTreeSearchTableImpl extends AbstractDynamicSearchTable {

    public BinarySortTreeImpl tree = new BinarySortTreeImpl();

    /**
     * 如果没有查到，则记录在查找路径上的最后一个节点
     */
    public BinaryTreeNode fNode;

    public List<String> values = new ArrayList<String>();

    @Override
    public Element search(Object key) {
        fNode = null;
        return searchKey(tree.node, key);
    }

    public Element searchKey (BinaryTreeNode node, Object key) {
        if (node == null) {
            return null;
        }
        if (((Element)node.data).equalKey(key) == 0) {
            return (Element) node.data;
        } else if (((Element)node.data).equalKey(key) > 0) {
            fNode = node;
            return searchKey(node.lChild, key);
        } else {
            fNode = node;
            return searchKey(node.rChild, key);
        }
    }

    @Override
    public boolean insert(Element e) {
        Element search = search(e.getKey());
        //没找到
        if (search == null) {
            BinaryTreeNode node = new BinaryTreeNode(e);
            if (tree.getRoot() == null) {
                tree.setRoot(node);
            } else {
                Element ele = (Element)(fNode.data);
                if (ele.equalKey(e.getKey()) > 0) {
                    tree.insertChild(fNode, 0, node);
                } else {
                    tree.insertChild(fNode, 1, node);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        //找到之后删除
        Element search = search(key);
        if (search == null) {
            return false;
        }
        if (fNode == null) {
            tree.deleteRoot(tree.getRoot());
        } else if (((Element)fNode.data).equalKey(key) > 0) {
            tree.deleteChild(fNode, 0);
        }else {
            tree.deleteChild(fNode, 1);
        }
        return  true;
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

    public void visit(Element data) {
        values.add(data.value);
    }
}
