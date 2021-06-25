package com.data.structure.graph.impl;

import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.AdjacentMatrixArc;
import com.data.structure.graph.model.vertex.AdjacentMatrixVerTex;

/**
 * AdjacentMatrix:
 * 图的数组（邻接矩阵）存储方式
 * 用于存储无向图，有向图，无向网，有向网
 *
 * @author sunchen
 * @date 2020/5/29 10:25 上午
 */
public class AdjacentMatrixGraphImpl extends AbstractGraph<AdjacentMatrixVerTex> {

    public static Integer MAX_VALUE = 1000000;
    /**
     * 邻接矩阵
     */
    public AdjacentMatrixArc[][] adjMatrix;

    /**
     * 图的种类
     */
    GraphType kind;

    /**
     * 创建邻接矩阵
     *
     * @param kind    类型
     * @param verTexs 顶点数组
     * @param triples (边，弧)关系数组
     */
    public AdjacentMatrixGraphImpl(GraphType kind, Object[] verTexs, TripleExtend[] triples) {
        // 1.根据入参，确定要创建的是哪种类型的图
        this.kind = kind;

        // 2.初始化顶点数组和邻接矩阵
        vexs = new AdjacentMatrixVerTex[MAX_SIZE];
        adjMatrix = new AdjacentMatrixArc[MAX_SIZE][MAX_SIZE];

        // 3. 创建图
        create(verTexs, triples);
    }

    @Override
    public Object firstAdjVex(Object vex) {
        int index = locateVex(vex);
        return vexs[findAdjVexByLocate(index, 0)].data;
    }

    @Override
    public Object nextAdjVex(Object vex, Object w) {
        int index = locateVex(vex);
        int col = locateVex(w);
        int i = findAdjVexByLocate(index, col + 1);
        if (i < 0) {
            return null;
        }
        return vexs[findAdjVexByLocate(index, col + 1)].data;
    }

    @Override
    public void insertVex(Object vex) {
        if (vexNum == MAX_SIZE) {
            throw new RuntimeException("顶点已满");
        }
        AdjacentMatrixVerTex adjacentMatrixVerTex = new AdjacentMatrixVerTex(vex);
        vexs[vexNum] = adjacentMatrixVerTex;
        vexNum++;
        //矩阵初始化一行一列
        for (int i = 0; i < vexNum; i++) {
            initArc(vexNum - 1, i);
            initArc(i, vexNum - 1);
        }
    }

    @Override
    public void deleteVex(Object vex) {
        int index = locateVex(vex);
        // 修改边或弧的个数
        arcNum -= getTotalDegree(vex);
        // 移动顶点数组
        if (vexNum - 1 - index >= 0) {
            System.arraycopy(vexs, index + 1, vexs, index, vexNum - 1 - index);
        }
        // 移动矩阵
        for (int row = index; row < vexNum - 1; row++) {
            if (index >= 0) {
                System.arraycopy(adjMatrix[row + 1], 0, adjMatrix[row], 0, index);
            }
        }
        for (int row = 0; row < index; row++) {
            if (vexNum - 1 - index >= 0) {
                System.arraycopy(adjMatrix[row], index + 1, adjMatrix[row], index, vexNum - 1 - index);
            }
        }

        for (int row = index; row < vexNum - 1; row++) {
            if (vexNum - 1 - index >= 0) {
                System.arraycopy(adjMatrix[row + 1], index + 1, adjMatrix[row], index, vexNum - 1 - index);
            }
        }
        vexNum--;
    }

    /**
     * 插入一条弧关系
     */
    @Override
    public void insertArc(TripleExtend data) {
        int row = locateVex(data.arcTail);
        int col = locateVex(data.arcHead);

        if (adjMatrix[row][col].adj != 0 && !MAX_VALUE.equals(adjMatrix[row][col].adj)) {
            return;
        }
        arcNum++;
        switch (kind) {
            // 到Directed结束
            case UnDirected:
                adjMatrix[col][row] = new AdjacentMatrixArc(0);
            case Directed:
                adjMatrix[row][col] = new AdjacentMatrixArc(1);
                break;
            // 到DirectedNetWork结束
            case UnDirectedNetWork:
                adjMatrix[col][row] = new AdjacentMatrixArc(data.weight);
            case DirectedNetWork:
                adjMatrix[row][col] = new AdjacentMatrixArc(data.weight);
                break;
            default:
        }
    }

    /**
     * 删除边或弧
     *
     * @param v1
     * @param v2
     */
    @Override
    public void deleteArc(Object v1, Object v2) {
        int row = locateVex(v1);
        int col = locateVex(v2);
        switch (kind) {
            case Directed:
            case DirectedNetWork:
                initArc(row, col);
                break;
            case UnDirectedNetWork:
            case UnDirected:
                initArc(row, col);
                initArc(col, row);
                break;
            default:
        }
        arcNum--;
    }

    public String verToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vexNum; i++) {
            sb.append(vexs[i].data).append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public Integer[][] toArray() {
        Integer[][] array = new Integer[vexNum][vexNum];
        for (int row = 0; row < vexNum; row++) {
            for (int col = 0; col < vexNum; col++) {
                array[row][col] = adjMatrix[row][col].adj;
            }
        }
        return array;
    }

    /**
     * 根据位置找顶点的邻接点
     *
     * @param row
     * @param col
     * @return
     */
    private int findAdjVexByLocate(int row, int col) {
        for (int j = col; j < vexNum; j++) {
            switch (kind) {
                case Directed:
                case UnDirected:
                    if (adjMatrix[row][j].adj == 1) {
                        return j;
                    }
                    break;
                case DirectedNetWork:
                case UnDirectedNetWork:
                    if (!MAX_VALUE.equals(adjMatrix[row][j].adj)) {
                        return j;
                    }
                    break;
                default:
            }
        }
        return -1;
    }

    /**
     * 初始化边或弧
     *
     * @param row
     * @param col
     */
    private void initArc(int row, int col) {
        switch (kind) {
            case Directed:
            case UnDirected:
                adjMatrix[row][col] = new AdjacentMatrixArc(0);
                break;
            case DirectedNetWork:
            case UnDirectedNetWork:
                if (row == col) {
                    adjMatrix[row][col] = new AdjacentMatrixArc(0);
                } else {
                    adjMatrix[row][col] = new AdjacentMatrixArc(MAX_VALUE);
                }
                break;
            default:
        }
    }

    /**
     * 获取顶点的度
     *
     * @param verTex
     * @return
     */
    private int getTotalDegree(Object verTex) {
        int index = locateVex(verTex);
        int degree = 0;
        switch (kind) {
            // 到UnDirected结束
            case Directed:
                for (int i = 0; i < vexNum; i++) {
                    if (adjMatrix[i][index].adj == 1 && index != i) {
                        degree++;
                    }
                }
            case UnDirected:
                for (int i = 0; i < vexNum; i++) {
                    if (adjMatrix[index][i].adj == 1) {
                        degree++;
                    }
                }
                break;
            // 到DirectedNetWork结束
            case DirectedNetWork:
                for (int i = 0; i < vexNum; i++) {
                    if (!MAX_VALUE.equals(adjMatrix[i][index].adj) && index != i) {
                        degree++;
                    }
                }
            case UnDirectedNetWork:
                for (int j = 0; j < vexNum; j++) {
                    if (!MAX_VALUE.equals(adjMatrix[index][j].adj)) {
                        degree++;
                    }
                }
                break;
            default:
        }
        return degree;
    }
}
