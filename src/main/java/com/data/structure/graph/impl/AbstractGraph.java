package com.data.structure.graph.impl;

import com.data.structure.graph.interfaces.Graph;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.vertex.AbstractVerTex;
import com.data.structure.queue.impl.SequenceQueueImpl;
import com.data.structure.stack.impl.SequenceStackImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 图的基类
 *
 * @author zhangshuai
 */
public abstract class AbstractGraph<VERTEX extends AbstractVerTex> implements Graph<VERTEX> {
    /**
     * 顶点的最大个数
     */
    public static int MAX_SIZE = 20;

    /**
     * 队列
     */
    public SequenceQueueImpl queue = new SequenceQueueImpl();

    /**
     * 栈
     */
    public SequenceStackImpl stack = new SequenceStackImpl();

    /**
     * 顶点向量
     */
    public VERTEX[] vexs;

    /**
     * 顶点个数，弧数
     */
    public int vexNum, arcNum;

    /**
     * 标识顶点是否为访问
     */
    public boolean[] isVisited;

    public List<Object> data = new ArrayList<Object>();

    /**
     * 创建图
     *
     * @param verTexs
     * @param triples
     */
    public void create(Object[] verTexs, TripleExtend[] triples) {
        // 建立顶点
        for (Object verTex : verTexs) {
            insertVex(verTex);
        }
        // 建立弧（边）
        for (TripleExtend triple : triples) {
            insertArc(triple);
        }
    }

    @Override
    public int locateVex(Object vexName) {
        for (int i = 0; i < vexNum; i++) {
            if (vexName.equals(vexs[i].data)) {
                return i;
            }
        }
        throw new RuntimeException("no find" + vexName);
    }

    @Override
    public Object getVex(VERTEX vex) {
        return vex.data;
    }

    @Override
    public void put(Object vexName, Object newName) {
        int index = locateVex(vexName);
        vexs[index].data = newName;
    }

    /**
     * 返回顶点的第一个邻接点
     *
     * @param vexName
     * @return
     */
    @Override
    abstract public Object firstAdjVex(Object vexName);

    /**
     * w是v的邻接顶点，返回下一个邻接顶点
     *
     * @param vexName
     * @param nextVexName
     * @return
     */
    @Override
    abstract public Object nextAdjVex(Object vexName, Object nextVexName);

    /**
     * 插入一个顶点
     *
     * @param vexName
     */
    @Override
    abstract public void insertVex(Object vexName);

    /**
     * 删除顶点
     *
     * @param vexName
     */
    @Override
    abstract public void deleteVex(Object vexName);

    /**
     * 插入边
     *
     * @param data
     */
    @Override
    abstract public void insertArc(TripleExtend data);

    /**
     * 删除边
     *
     * @param vexName1
     * @param vexName2
     */
    @Override
    abstract public void deleteArc(Object vexName1, Object vexName2);

    @Override
    public void dfsTraverse() {
        //初始化isVisited
        isVisited = new boolean[vexNum];
        for (int i = 0; i < vexNum; i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    public void dfs(int vexIndex) {
        //1.访问顶点
        visit(vexs[vexIndex].data);
        isVisited[vexIndex] = true;
        Object adjVex = firstAdjVex(vexs[vexIndex].data);
        while (adjVex != null) {
            int adjIndex = locateVex(adjVex);
            if (!isVisited[adjIndex]) {
                dfs(adjIndex);
            }
            adjVex = nextAdjVex(vexs[vexIndex].data, adjVex);
        }
    }

    /**
     * 广度优先遍历
     */
    @Override
    public void bfsTraverse() {
        isVisited = new boolean[vexNum];
        for (int i = 0; i < vexNum; i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
    }

    public void bfs(int vexIndex) {
        isVisited[vexIndex] = true;
        visit(vexs[vexIndex].data);
        queue.enQueue(vexIndex);
        while (!queue.queueEmpty()) {
            Integer index = (Integer) queue.deQueue();
            Object adjVexName = firstAdjVex(vexs[index].data);
            while (adjVexName != null) {
                int adj = locateVex(adjVexName);
                if (!isVisited[adj]) {
                    visit(adjVexName);
                    queue.enQueue(locateVex(adjVexName));
                    isVisited[adj] = true;
                }
                adjVexName = nextAdjVex(vexs[index].data, adjVexName);
            }
        }

    }

    /**
     * 访问函数
     *
     * @param vex
     */
    public void visit(Object vex) {
        data.add(vex);
        System.out.println(vex);
    }

    /**
     * 输出二维数组
     *
     * @return
     */
    @Override
    abstract public Integer[][] toArray();
}
