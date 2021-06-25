package com.data.algorithm.graph;

import com.data.structure.graph.impl.AdjacentMatrixGraphImpl;
import com.data.structure.graph.model.CloseEdge;
import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import java.util.ArrayList;
import java.util.List;

/**
 * AdjacentMatrixGraphOp:
 * 邻接矩阵运用
 *
 * @author sunchen
 * @date 2020/6/9 11:30 上午
 */
public class AdjacentMatrixGraphOp extends AdjacentMatrixGraphImpl {
    CloseEdge[] closeEdges;

    public boolean[][] p;
    public int[] d;

    public boolean[][][] pFloyd;
    public int[][] dFloyd;


    /**
     * 创建邻接矩阵
     *
     * @param kind    类型
     * @param verTexs 顶点数组
     * @param triples (边，弧)关系数组
     */
    public AdjacentMatrixGraphOp(GraphType kind, Object[] verTexs, TripleExtend[] triples) {
        super(kind, verTexs, triples);
    }

    /**
     * 用prim算法 从vexName顶点开始，计算最小生成树
     *
     * @param vexName
     * @return
     */
    public List<TripleExtend> miniSpanTreePrim(String vexName) {
        //1.边集合
        List<TripleExtend> edgeList = new ArrayList<TripleExtend>();
        //2.起始顶点在顶点数组的下标
        int uIndex = locateVex(vexName);
        //3.初始化辅助数组
        closeEdges = new CloseEdge[vexNum];
        for (int vex = 0; vex < vexNum; vex++) {
            if (vex == uIndex) {
                closeEdges[vex] = new CloseEdge(vex, 0);
                continue;
            }
            closeEdges[vex] = new CloseEdge(uIndex, adjMatrix[uIndex][vex].adj);
        }
        //4.计算出权最小的边，将边存入边集合中，将顶点放入U中
        for (int vex = 1; vex < vexNum; vex++) {
            //4.1计算权重最小的边，并返回边依附的顶点在closeEdges数组的下标
            uIndex = minimum(closeEdges);
            //4.2把边存入边集合中
            TripleExtend tripleExtend = new TripleExtend(vexs[closeEdges[uIndex].adjVex].data, vexs[uIndex].data, closeEdges[uIndex].lowCost);
            edgeList.add(tripleExtend);
            //4.3把顶点加入到U中
            closeEdges[uIndex].lowCost = 0;
            //4.4因为U加入了一个顶点，需要其他的顶点再计算一下和U中哪一个顶点组成的边权重最小
            for (int j = 0; j < vexNum; j++) {
                if (closeEdges[j].lowCost != 0) {
                    if (adjMatrix[uIndex][j].adj < closeEdges[j].lowCost) {
                        closeEdges[j].lowCost = adjMatrix[uIndex][j].adj;
                        closeEdges[j].adjVex = uIndex;
                    }
                }
            }
        }
        return edgeList;
    }

    /**
     * 最短路径-迪杰斯特拉算法 求一个顶点到其他各个顶点的最短路径
     */
    public void shortestPathDlj (int startVex) {
        boolean[] finalArray = new boolean[vexNum];
        //记录从指定顶点到各个顶点经过了哪些顶点
        p = new boolean[vexNum][vexNum];
        //记录从指定顶点到各个顶点的最短路径的权和。
        d = new int[vexNum];
        //初始化d和p
        for (int i = 0; i < vexNum; i++) {
            d[i] = adjMatrix[startVex][i].adj;
            for (int j = 0; j < vexNum; j++) {
                p[i][j] = false;
            }
            if (d[i] < MAX_VALUE) {
                p[i][startVex] = true;
                p[i][i] = true;
            }
        }
        //2.循环顶点，取每个顶点，计算她和其他顶点的d,找到最小的，放入final中，然后重新计算其他顶点的d
        d[startVex] = 0; finalArray[startVex] = true;
        int v = 0;
        for (int i = 1; i < vexNum; i++) {
            int min = MAX_VALUE;
            //求权最小的边
            for (int j = 0; j < vexNum; j++) {
                if (!finalArray[j] && d[j] < min) {
                    min = d[j];
                    v = j;
                }
            }
            //将边的另一个顶点添加到S中
            finalArray[v] = true;
            for (int j = 0; j < vexNum; j++) {
                if (!finalArray[j] && adjMatrix[v][j].adj + min < d[j]) {
                    d[j] = adjMatrix[v][j].adj + d[v];
                    System.arraycopy(p[v], 0, p[j], 0, vexNum);
                    p[j][j] = true;
                }
            }
        }
    }

    /**
     * 弗洛伊德-最短路径
     */
    public void shortestFloyd () {
        //1. 初始化 d[][] p[][][]
        dFloyd = new int[vexNum][vexNum];
        pFloyd = new boolean[vexNum][vexNum][vexNum];
        for (int i = 0; i < vexNum; i++) {
            for (int j = 0; j < vexNum; j++) {
                dFloyd[i][j] = adjMatrix[i][j].adj;
                for (int k = 0; k < vexNum; k++) {
                    pFloyd[i][j][k] = false;
                }
                if (dFloyd[i][j] != MAX_VALUE && i != j) {
                    pFloyd[i][j][i] = true;
                    pFloyd[i][j][j] = true;
                }
            }
        }
        //2.往弧<j,k>中插入一个顶点i,如果<j,i>+<i,k>的值小于<j,k>，则更新dFloyd和pFloyd
        for (int i = 0; i < vexNum; i++) {
            for (int j = 0; j < vexNum; j++) {
                for (int k = 0; k < vexNum; k++) {
                    if (dFloyd[j][i] + dFloyd[i][k] < dFloyd[j][k]) {
                        dFloyd[j][k] = dFloyd[j][i] + dFloyd[i][k];
                        //更新p
                        for (int v = 0; v < vexNum; v++) {
                            pFloyd[j][k][v] = pFloyd[j][i][v] || pFloyd[i][k][v];
                        }
                    }
                }
            }
        }
    }

    private int minimum(CloseEdge[] closeEdges) {
        int index = 0;
        Integer weight = MAX_VALUE;
        for (int i = 0; i < closeEdges.length; i++) {
            if (closeEdges[i].lowCost == 0) {
                continue;
            }
            if (closeEdges[i].lowCost < weight) {
                weight = closeEdges[i].lowCost;
                index = i;
            }
        }
        return index;
    }

}
