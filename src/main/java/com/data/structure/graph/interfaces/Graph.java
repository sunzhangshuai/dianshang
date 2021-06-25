package com.data.structure.graph.interfaces;

import com.data.structure.graph.model.TripleExtend;

/**
 * @author sunchen
 */
public interface Graph<T> {

    /**
     * 获取顶点的位置
     *
     * @param vexName
     * @return
     */
    public abstract int locateVex(Object vexName);

    /**
     * 返回顶点的值域
     *
     * @param vex
     * @return
     */
    Object getVex(T vex);

    /**
     * 为顶点赋值
     *
     * @param vexName
     * @param newName
     */
    void put(Object vexName, Object newName);

    /**
     * 返回顶点的第一个邻接点
     *
     * @param vexName
     * @return
     */
    Object firstAdjVex(Object vexName);

    /**
     * w是v的邻接顶点，返回下一个邻接顶点
     *
     * @param vexName
     * @param nextVexName
     * @return
     */
    Object nextAdjVex(Object vexName, Object nextVexName);

    /**
     * 插入一个顶点
     *
     * @param vexName
     */
    void insertVex(Object vexName);

    /**
     * 删除顶点
     *
     * @param vexName
     */
    void deleteVex(Object vexName);

    /**
     * 添加边或者弧
     *
     * @param data
     */
    void insertArc(TripleExtend data);

    /**
     * 删除弧或者边
     *
     * @param vexName1
     * @param vexName2
     */
    void deleteArc(Object vexName1, Object vexName2);

    /**
     * 深度优先
     */
    void dfsTraverse();

    /**
     * 广度优先
     */
    void bfsTraverse();

    /**
     * 将邻接矩阵中的弧或边转成数组
     *
     * @return
     */
    Object[][] toArray();

}
