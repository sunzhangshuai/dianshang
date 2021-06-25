package com.data.structure.graph.impl;

import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.AdjacentListArc;
import com.data.structure.graph.model.vertex.AdjacentListVerTex;

/**
 * AdjacentListGraphImpl:
 * 邻接表 用于存储无向图和有向图
 *
 * @author sunchen
 * @date 2020/5/30 1:11 下午
 */
public class AdjacentListGraphImpl extends AbstractGraph<AdjacentListVerTex> {
    /**
     * 图的种类
     */
    public GraphType kind;

    public AdjacentListGraphImpl(GraphType kind, String[] verTexs, TripleExtend[] triples) {
        this.kind = kind;
        this.vexs = new AdjacentListVerTex[MAX_SIZE];
        create(verTexs, triples);
    }

    @Override
    public Object firstAdjVex(Object vexName) {
        int index = locateVex(vexName);
        AdjacentListArc arc = vexs[index].firstArc;
        if (arc != null) {
            return vexs[arc.adjVex].data;
        }
        return null;
    }

    @Override
    public Object nextAdjVex(Object vex, Object next) {
        int index = locateVex(vex);
        int nextVexIndex = locateVex(next);
        AdjacentListArc arc = vexs[index].firstArc;
        while (arc != null && arc.adjVex != nextVexIndex) {
            arc = arc.nextArc;
        }
        if (arc != null && arc.nextArc != null) {
            return vexs[arc.nextArc.adjVex].data;
        }
        return null;
    }

    /**
     * 增加一个顶点
     *
     * @param vexName
     */
    @Override
    public void insertVex(Object vexName) {
        if (vexNum == MAX_SIZE) {
            throw new RuntimeException("顶点已满");
        }
        AdjacentListVerTex adjacentListVerTex = new AdjacentListVerTex(vexName);
        vexs[vexNum] = adjacentListVerTex;
        vexNum++;
    }

    @Override
    public void deleteVex(Object vex) {
        //找到要删除的结点
        int index = locateVex(vex);
        //1. 计算结点的出度(有向) 或者 计算结点的度（无向）
        AdjacentListArc arcNode = vexs[index].firstArc;
        int count = 0;
        while (arcNode != null) {
            count++;
            arcNode = arcNode.nextArc;
        }
        //2. 删除以该结点为入度的弧结点
        for (int i = 0; i < vexNum; i++) {
            arcNode = vexs[i].firstArc;
            if (arcNode.adjVex == index) {
                vexs[i].firstArc = arcNode.nextArc;
                if (kind == GraphType.Directed) {
                    count++;
                }
                break;
            } else {
                while (arcNode.nextArc != null) {
                    if (arcNode.nextArc.adjVex == index) {
                        //删除结点
                        arcNode.nextArc = arcNode.nextArc.nextArc;
                        if (kind == GraphType.Directed) {
                            count++;
                        }
                        break;
                    }
                    arcNode = arcNode.nextArc;
                }
            }
        }
        arcNum -= count;
        //删除行后，把下面的行全部上移
        if (vexNum - 1 - index >= 0) {
            System.arraycopy(vexs, index + 1, vexs, index, vexNum - 1 - index);
        }
        vexNum--;
        //修改下标大于该结点下标的值
        for (int i = 0; i < vexNum; i++) {
            AdjacentListVerTex head = vexs[i];
            AdjacentListArc firstArcNode = head.firstArc;
            while (firstArcNode != null) {
                if (firstArcNode.adjVex > index) {
                    firstArcNode.adjVex--;
                }
                firstArcNode = firstArcNode.nextArc;
            }
        }

    }

    @Override
    public void insertArc(TripleExtend data) {
        //获取弧尾结点名
        Object verTexTailName = data.arcTail;
        //获取弧头结点名
        Object verTexHeadName = data.arcHead;
        //根据弧尾,弧尾结点名找到顶点在数组中的位置
        int indexTail = locateVex(verTexTailName);
        int indexHead = locateVex(verTexHeadName);
        switch (kind) {
            //如果是无向图，需要建立对称关系
            case UnDirected:
                insertFirst(indexHead, indexTail, data.weight);
            case Directed:
                insertFirst(indexTail, indexHead, data.weight);
                break;
            default:
        }
        arcNum++;
    }

    /**
     * 往指定链表头插一个结点
     *
     * @param index
     */
    public void insertFirst(int index, int arcNodeIndex, int weight) {
        //获取弧尾头结点
        AdjacentListVerTex head = vexs[index];
        //创建新结点
        AdjacentListArc adjacentListArc = new AdjacentListArc(arcNodeIndex, weight);
        //头插
        adjacentListArc.nextArc = head.firstArc;
        head.firstArc = adjacentListArc;
    }

    @Override
    public void deleteArc(Object v1, Object v2) {
        int tailIndex = locateVex(v1);
        int headIndex = locateVex(v2);
        switch (kind) {
            case UnDirected:
                delete(headIndex, tailIndex);
            case Directed:
                delete(tailIndex, headIndex);
                break;
            default:
        }
        arcNum--;
    }

    /**
     * 删除链表中的一个邻接点
     *
     * @param row
     * @param index
     */
    public void delete(int row, int index) {
        //删除V1和V2的关系，在v1链表中找V2
        AdjacentListArc arcNode = vexs[row].firstArc;
        if (arcNode.adjVex == index) {
            vexs[row].firstArc = arcNode.nextArc;
        } else {
            while (arcNode.nextArc != null) {
                if (arcNode.nextArc.adjVex == index) {
                    //删除结点
                    arcNode.nextArc = arcNode.nextArc.nextArc;
                    break;
                }
                arcNode = arcNode.nextArc;
            }
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
        for (int row = 0; row < vexNum; row++) {
            AdjacentListArc arcNode = vexs[row].firstArc;
            while (arcNode != null) {
                int col = arcNode.adjVex;
                array[row][col] = 1;
                arcNode = arcNode.nextArc;
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
