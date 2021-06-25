package com.data.structure.graph.model.arc;

/**
 * AdjacencyMuiListArc:
 * 多重邻接表 边结点
 * @author sunchen
 * @date 2020/6/2 3:29 下午
 */
public class AdjacencyMuiListArc extends AbstractArc{
    /**
     * 标记，是否被访问过
     */
    public int mark;
    /**
     * 边依附的两个顶点位置
     */
    public int iVex, jVex;

    /**
     * 分别指向依附这两个顶点的下一条边
     */
    public AdjacencyMuiListArc iLink, jLink;

    public AdjacencyMuiListArc(int iVex, int jVex){
        this.iVex = iVex;
        this.jVex = jVex;
    }
}
