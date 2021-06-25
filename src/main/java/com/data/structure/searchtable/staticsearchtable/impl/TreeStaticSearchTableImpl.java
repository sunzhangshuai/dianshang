package com.data.structure.searchtable.staticsearchtable.impl;

import com.data.structure.searchtable.model.Element;
import com.data.structure.tree.impl.binarytree.BinaryTreeImpl;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeStaticSearchTable:
 * 静态树表
 *
 * @author sunchen
 * @date 2020/6/21 2:07 下午
 */
public class TreeStaticSearchTableImpl extends AbstractStaticSearchTable<Element> {

    public BinaryTreeImpl tree = new BinaryTreeImpl();

    public int[] sumWeight;

    public List<String> valueDatas;

    public TreeStaticSearchTableImpl(Element[] data) {
        length = data.length;
        elementData = new Element[length + 1];
        System.arraycopy(data, 0, elementData, 1, data.length);
        //1.排序
        sort();
        //2.计算sumWeight
        getSumWeight();
        //3.生成静态树
        createBiTree();
    }

    public void createBiTree() {
        //生成根
        if (length == 0) {
        }
        tree.node = secondOptimal(1, length);
    }

    public BinaryTreeNode secondOptimal(int low, int high) {
        // 确定mid
        int min = Math.abs(sumWeight[high] - sumWeight[low]);
        int dum = Math.abs(sumWeight[high] + sumWeight[low - 1]);
        int mid = low;
        for (int i = low + 1; i <= high; i++) {
            //计算
            if (min > Math.abs(dum - sumWeight[i] - sumWeight[i - 1])) {
                min = dum - sumWeight[mid] - sumWeight[mid - 1];
                mid = i;
            }
        }

        // 和附近的比较概率
        int max = elementData[mid].weight;
        int maxIndex = mid;
        for (int i = mid - 1; i < mid + 1 && i >= low && i <= high; i++) {
            if (elementData[i].weight > max) {
                max = elementData[i].weight;
                maxIndex = i;
            }
        }
        mid = maxIndex;

        // 创建结点
        BinaryTreeNode node = new BinaryTreeNode(mid);
        if (mid == low) {
            node.lChild = null;
        } else {
            node.lChild = secondOptimal(low, mid - 1);
        }

        if (mid == high) {
            node.rChild = null;
        } else {
            node.rChild = secondOptimal(mid + 1, high);
        }
        return node;
    }

    /**
     * 计算sumWight
     */
    private void getSumWeight() {
        sumWeight = new int[length + 1];
        sumWeight[0] = 0;
        for (int i = 1; i <= length; i++) {
            sumWeight[i] = sumWeight[i - 1] + elementData[i].weight;
        }
    }

    @Override
    public Element search(Object key) {
        BinaryTreeNode node = tree.node;

        while (node != null) {
            int index = (Integer) node.data;
            if (elementData[index].equalKey(key) == 0) {
                return elementData[index];
            } else if (elementData[index].equalKey(key) > 0) {
                node = node.lChild;
            } else {
                node = node.rChild;
            }
        }
        throw new RuntimeException("没有找到:" + key);
    }

    @Override
    public void traverse() {
        tree.preOrderTraverse();
        List<Object> datas = tree.datas;
        valueDatas = new ArrayList<String>(datas.size());
        for (int i = 0; i < datas.size(); i++) {
            valueDatas.add(elementData[i + 1].value);
        }
    }
}
