package com.data.structure.graph.model.arc;

/**
 * OrthogonalListArc:
 * 十字链表弧结点
 *
 * @author sunchen
 * @date 2020/6/1 4:16 下午
 */
public class OrthogonalListArc extends AbstractArc {
    /**
     * 弧尾下标
     */
    public int tailVex;
    /**
     * 弧头下标
     */
    public int headVex;
    /**
     * 指向下一个弧头相同的弧结点
     */
    public OrthogonalListArc hLink;
    /**
     * 指向下一个狐尾相同的弧结点
     */
    public OrthogonalListArc tLink;

    public OrthogonalListArc(int tailVex, int headVex) {
        this.tailVex = tailVex;
        this.headVex = headVex;
    }
}
