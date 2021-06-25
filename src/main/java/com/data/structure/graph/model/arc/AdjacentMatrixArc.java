package com.data.structure.graph.model.arc;

/**
 * AdjacentMatrixArc:
 *
 * @author sunchen
 * @date 2020/5/29 11:30 上午
 */
public class AdjacentMatrixArc extends AbstractArc {

    /**
     * 顶点关系类型
     * 图：用0huo1表示是否相邻
     * 网：权值类型，不相临为∞
     */
    public Integer adj;

    public AdjacentMatrixArc(Integer arc) {
        this.adj = arc;
    }
}
