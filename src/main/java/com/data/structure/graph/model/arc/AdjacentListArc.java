package com.data.structure.graph.model.arc;

/**
 * AdjacentListArc:
 *
 * @author sunchen
 * @date 2020/5/30 1:12 下午
 */
public class AdjacentListArc extends AbstractArc {
    public int info;
    /**
     * 下一条邻接弧或边
     */
    public AdjacentListArc nextArc;

    /**
     * 邻接点下标
     */
    public int adjVex;

    public AdjacentListArc(int adjVex) {
        this.adjVex = adjVex;
    }

    public AdjacentListArc(int adjVex, int info) {
        this.adjVex = adjVex;
        this.info = info;
    }
}
