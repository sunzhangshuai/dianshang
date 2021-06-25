package com.data.structure.searchtable.dynamicsearchtable.impl;

import com.data.structure.searchtable.model.Element;
import com.data.structure.tree.impl.tree.BtreeImpl;
import com.data.structure.tree.model.treenode.BalanceTreeNode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BTreeSortTreeSearchTableImpl:
 * b-树 查询 插入 删除
 *
 * @author sunchen
 * @date 2020/7/2 4:19 下午
 */
public class BtreeSortTreeSearchTableImpl extends AbstractDynamicSearchTable {

    /**
     * m阶树
     */
    @Autowired
    private BtreeImpl btree;

    public BtreeImpl getBtree() {
        return btree;
    }

    public void setBtree(BtreeImpl btree) {
        this.btree = btree;
    }

    /**
     * 如果找不到遍历过程经过的最后一个结点
     */
    public BalanceTreeNode fNode;

    /**
     * 遍历到的最后一个结点的哪一个位置
     */
    public int index;

    /**
     * 先找到结点，再找到结点里面的key
     *
     * @param key
     * @return
     */
    @Override
    public Element search(Object key) {
        int searchKey = (Integer)key;
        Element search = search(btree.node, searchKey);
        if (search != null) {
            return null;
        }
        return search(btree.node, (Integer) key);
    }

    public Element search(BalanceTreeNode node, int key) {
        int i = 1;
        if (node == null) {
            return null;
        }
        for (; i < node.keyNum; i++) {
            if (node.key[i] >= key && node.key[i + 1] < key) {
                break;
            }
        }
        if (node.key[i] == key) {
            fNode = node;
            return node.recPtr[i];
        } else {
            fNode = node;
            return search(node.ptr[i], key);
        }
    }

    @Override
    public boolean insert(Element e) {
        return false;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }
}
