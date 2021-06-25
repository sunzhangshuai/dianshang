package com.data.structure.matrix.model;

/**
 * OLNode:
 * 十字链表的结点
 * @author sunchen
 * @date 2020/5/21 11:25 上午
 */
public class OlNode {
    /**
     * 该非零元的行和列下标
     */
    public int row, col;
    public int e;
    /**
     * 该非零元所在行表和列表的后继链域
     */
    public OlNode right, down;

    public OlNode(int row, int col, int e){
        this.row = row;
        this.col = col;
        this.e = e;
    }
}
