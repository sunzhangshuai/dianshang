package com.data.structure.graph.model;

/**
 * CloseEdge:
 * 求最小生成树的辅助数组
 *
 * @author sunchen
 * @date 2020/6/9 2:27 下午
 */
public class CloseEdge {
    /**
     * 存储边依附的在U中的顶点下标
     */
    public int adjVex;
    /**
     * 权
     */
    public Integer lowCost;

    public CloseEdge(int adjVex, Integer lowCost) {
        this.adjVex = adjVex;
        this.lowCost = lowCost;
    }
}
