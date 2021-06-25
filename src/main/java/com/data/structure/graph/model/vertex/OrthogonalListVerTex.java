package com.data.structure.graph.model.vertex;

import com.data.structure.graph.model.arc.OrthogonalListArc;

/**
 * OrthogonalListVerTex:
 * 十字链表顶点结点
 *
 * @author sunchen
 * @date 2020/6/1 4:17 下午
 */
public class OrthogonalListVerTex extends AbstractVerTex {
    /**
     * 指向该顶点的第一条入弧
     */
    public OrthogonalListArc firstIn;
    /**
     * 指向该顶点的第一条出弧
     */
    public OrthogonalListArc firstOut;

    public OrthogonalListVerTex(Object data) {
        super(data);
    }
}
