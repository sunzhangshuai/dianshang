package com.data.structure.graph.impl;

import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.AdjacencyMuiListArc;
import com.data.structure.graph.model.vertex.AdjacencyMuiListVerTex;

/**
 * AdjacencyMultListGraphImpl:
 * 多重邻接表
 *
 * @author sunchen
 * @date 2020/6/2 3:26 下午
 */
public class AdjacencyMultListGraphImpl extends AbstractGraph<AdjacencyMuiListVerTex> {

    /**
     * 初始化
     *
     * @param vexNames
     * @param triples
     */
    public AdjacencyMultListGraphImpl(String[] vexNames, TripleExtend[] triples) {
        this.vexs = new AdjacencyMuiListVerTex[MAX_SIZE];
        create(vexNames, triples);
    }

    @Override
    public Object firstAdjVex(Object vexName) {
        int index = locateVex(vexName);
        int firstAdjVexIndex = getNextAdjVexIndex(vexs[index].firstEdge, index);
        return vexs[firstAdjVexIndex].data;
    }

    @Override
    public Object nextAdjVex(Object vexName, Object nextVexName) {
        int index = locateVex(vexName);
        int nextVexIndex = locateVex(nextVexName);
        AdjacencyMuiListArc firstEdge = vexs[index].firstEdge;
        while (firstEdge != null && getNextAdjVexIndex(firstEdge, index) != nextVexIndex) {
            firstEdge = getNextArc(firstEdge, index);
        }
        if (firstEdge != null && getNextArc(firstEdge, index) != null) {
            return vexs[getNextAdjVexIndex(getNextArc(firstEdge, index), index)].data;
        }
        return null;
    }

    /**
     * 新增一个顶点
     *
     * @param vexName
     */
    @Override
    public void insertVex(Object vexName) {
        if (vexNum == MAX_SIZE) {
            throw new RuntimeException("顶点已满");
        }
        vexs[vexNum] = new AdjacencyMuiListVerTex(vexName);
        vexNum++;
    }

    /**
     * 删除顶点
     *
     * @param vexName
     */
    @Override
    public void deleteVex(Object vexName) {
        //1. 找到顶点所在的位置
        int index = locateVex(vexName);
        //2. 删除和顶点相关的边结点
        AdjacencyMuiListArc firstEdge = vexs[index].firstEdge;
        while (firstEdge != null) {
            deleteArc(vexName, vexs[getNextAdjVexIndex((firstEdge), index)].data);
            firstEdge = getNextArc(firstEdge, index);
        }
        //3. 移动需要移动的下标
        if (vexNum - index >= 0) {
            System.arraycopy(vexs, index + 1, vexs, index, vexNum - index);
        }
        vexNum--;
        for (int i = 0; i < vexNum; i++) {
            AdjacencyMuiListArc edgeNode = vexs[i].firstEdge;
            while (edgeNode != null) {
                if (edgeNode.mark == 0) {
                    if (edgeNode.iVex > index) {
                        edgeNode.iVex -= 1;
                    }
                    if (edgeNode.jVex > index) {
                        edgeNode.jVex -= 1;
                    }
                    edgeNode.mark = 1;
                }
                edgeNode = getNextArc(edgeNode, i);
            }
        }
    }

    /**
     * 新增边结点
     *
     * @param data
     */
    @Override
    public void insertArc(TripleExtend data) {
        Object edge1Name = data.arcTail;
        Object edge2Name = data.arcHead;
        int edgeIndex1 = locateVex(edge1Name);
        int edgeIndex2 = locateVex(edge2Name);
        //创建新结点
        AdjacencyMuiListArc newArc = new AdjacencyMuiListArc(edgeIndex1, edgeIndex2);
        newArc.iLink = vexs[edgeIndex1].firstEdge;
        newArc.jLink = vexs[edgeIndex2].firstEdge;
        vexs[edgeIndex1].firstEdge = newArc;
        vexs[edgeIndex2].firstEdge = newArc;
        arcNum++;
    }

    /**
     * 删除边结点
     *
     * @param vexName1
     * @param vexName2
     */
    @Override
    public void deleteArc(Object vexName1, Object vexName2) {
        int edgeIndex1 = locateVex(vexName1);
        int edgeIndex2 = locateVex(vexName2);
        //找到边结点，并删除
        AdjacencyMuiListArc firstEdge = vexs[edgeIndex1].firstEdge;
        if (getNextAdjVexIndex(firstEdge, edgeIndex1) == edgeIndex2) {
            vexs[edgeIndex1].firstEdge = getNextArc(firstEdge, edgeIndex1);
        } else {
            while (getNextAdjVexIndex(getNextArc(firstEdge, edgeIndex1), edgeIndex1) != edgeIndex2) {
                firstEdge = getNextArc(firstEdge, edgeIndex1);
            }
            //找到之后，断开链接
            deleteAfterEdge(firstEdge, edgeIndex1);
        }
        AdjacencyMuiListArc firstEdge2 = vexs[edgeIndex2].firstEdge;
        if (getNextAdjVexIndex(firstEdge2, edgeIndex2) == edgeIndex1) {
            vexs[edgeIndex2].firstEdge = firstEdge2.jLink;
        } else {
            while (getNextAdjVexIndex(getNextArc(firstEdge2, edgeIndex2), edgeIndex2) != edgeIndex1) {
                firstEdge2 = getNextArc(firstEdge2, edgeIndex2);
            }
            deleteAfterEdge(firstEdge2, edgeIndex2);
        }
    }

    /**
     * 根据边获取下一条边
     *
     * @param arc
     * @param index
     * @return
     */
    public AdjacencyMuiListArc getNextArc(AdjacencyMuiListArc arc, int index) {
        return index == arc.iVex ? arc.iLink : arc.jLink;
    }

    /**
     * 根据边获取邻接点位置
     *
     * @param arc
     * @param index
     * @return
     */
    public int getNextAdjVexIndex(AdjacencyMuiListArc arc, int index) {
        return index == arc.iVex ? arc.jVex : arc.iVex;
    }

    public void deleteAfterEdge(AdjacencyMuiListArc arc, int index) {
        if (index == arc.iVex) {
            arc.iLink = getNextArc(getNextArc(arc, index), index);
        } else {
            arc.jLink = getNextArc(getNextArc(arc, index), index);
        }
    }

    @Override
    public Integer[][] toArray() {
        Integer[][] array = new Integer[vexNum][vexNum];
        for (int row = 0; row < vexNum; row++) {
            for (int col = 0; col < vexNum; col++) {
                array[row][col] = 0;
            }
        }
        for (int i = 0; i < vexNum; i++) {
            AdjacencyMuiListArc firstEdge = vexs[i].firstEdge;
            while (firstEdge != null) {
                array[firstEdge.jVex][firstEdge.iVex] = 1;
                array[firstEdge.iVex][firstEdge.jVex] = 1;
                firstEdge = getNextArc(firstEdge, i);
            }
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vexNum; i++) {
            sb.append(vexs[i].data).append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
