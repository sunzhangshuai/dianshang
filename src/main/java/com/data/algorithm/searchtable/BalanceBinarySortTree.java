package com.data.algorithm.searchtable;

/**
 * BalanceBinarySortTree:
 *
 * @author sunchen
 * @date 2020/6/28 5:16 下午
 */
public class BalanceBinarySortTree {

    /**
     * 求n个节点的平衡二叉树的最大高度
     *高度 节点个数  斐波那契值
     * 1   2         1
     * 2   4         1
     * 3   7         2
     * 4   12        3
     * 5             5
     * 6             8
     * 7             13
     *
     *
     * @return
     */
    public int depth (int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 4;
        }
        return depth(i-1) + depth(i-2) + 1;
    }

    public static void main(String[] args) {
        BalanceBinarySortTree tree = new BalanceBinarySortTree();
        int depth = tree.depth(4);
        System.out.println(depth);
    }
}
