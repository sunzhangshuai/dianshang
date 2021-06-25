package com.data.algorithm.graph;

import com.data.structure.graph.impl.OrthogonalListGraphImpl;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.OrthogonalListArc;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * OrthogonalListGraphOp:
 *
 * @author sunchen
 * @date 2020/6/8 3:51 下午
 */
public class OrthogonalListGraphOp extends OrthogonalListGraphImpl {

    int[] finished;

    public Map<Integer, List<Object>> components = new LinkedHashMap<Integer, List<Object>>();

    public OrthogonalListGraphOp(String[] vexNames, TripleExtend[] triples) {
        super(vexNames, triples);
    }

    int count = 0;

    /**
     * 求强连通分量
     */
    public void stronglyConnectedComponent() {
        //初始化记录是否访问过结点的数组
        isVisited = new boolean[vexNum];
        //记录顶点被访问完的顺序
        finished = new int[vexNum];
        //1.顶点分类
        for (int i = 0; i < vexNum; i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
        count = 0;
        isVisited = new boolean[vexNum];
        //2.倒序遍历finish
        for (int i = vexNum - 1; i >= 0; i--) {
            if (!isVisited[finished[i]]) {
                clear();
                dfsReverse(finished[i]);
                components.put(count++, data);
            }
        }
    }

    @Override
    public void dfs(int vexIndex) {
        //1.访问顶点
        visit(vexIndex);
        isVisited[vexIndex] = true;
        OrthogonalListArc arcNode = vexs[vexIndex].firstOut;
        while (arcNode != null) {
            if (!isVisited[arcNode.headVex]) {
                dfs(arcNode.headVex);
            }
            arcNode = arcNode.tLink;
        }
        finished[count++] = vexIndex;
    }

    public void dfsReverse(int vexIndex) {
        //1.访问顶点
        visit(vexIndex);
        isVisited[vexIndex] = true;
        OrthogonalListArc arcNode = vexs[vexIndex].firstIn;
        while (arcNode != null) {
            if (!isVisited[arcNode.tailVex]) {
                dfsReverse(arcNode.tailVex);
            }
            arcNode = arcNode.hLink;
        }
    }
}
