package com.data.structure.graph.model.vertex;

import com.data.structure.graph.model.arc.AdjacencyMuiListArc;

/**
 * AdjacencyMuiListVerTex:
 * 多重邻接表顶点结点
 *
 * @author sunchen
 * @date 2020/6/2 3:30 下午
 */
public class AdjacencyMuiListVerTex extends AbstractVerTex {
    /**
     * 指向第一条依附该顶点的边
     */
    public AdjacencyMuiListArc firstEdge;

    public AdjacencyMuiListVerTex(Object data) {
        super(data);
    }
}
