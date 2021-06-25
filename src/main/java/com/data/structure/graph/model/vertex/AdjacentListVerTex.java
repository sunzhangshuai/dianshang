package com.data.structure.graph.model.vertex;

import com.data.structure.graph.model.arc.AdjacentListArc;

/**
 * VexNode:
 * 邻接表-头结点
 *
 * @author sunchen
 * @date 2020/5/30 1:13 下午
 */
public class AdjacentListVerTex extends AbstractVerTex {

    /**
     * 第一条弧
     */
    public AdjacentListArc firstArc;

    public AdjacentListVerTex(Object data) {
        super(data);
    }
}
