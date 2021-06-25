package com.data.algorithm.graph;

import com.data.structure.graph.impl.AdjacentListGraphImpl;
import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.AdjacentListArc;
import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.tree.impl.binarytree.BinaryTreeImpl;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * AdjacentListGraphOp:
 * 把用邻接矩阵存储的图（可以是连通图也可以是非连通图）转成树（森林用孩子兄弟表示法存储）
 *
 * @author sunchen
 * @date 2020/6/5 10:33 上午
 */
public class AdjacentListGraphOp extends AdjacentListGraphImpl {
    int count = 1;

    /**
     * 按照拓扑排序获取到的顶点名称
     */
    public List<String> vexName = new ArrayList<String>();

    public int[] visit;

    int[] low;

    public int[] inDegreeArray;

    public int[] ve;

    public int[] vl;

    public SequenceStackImpl toPoStack = new SequenceStackImpl();

    public List<TripleExtend>  edgeList = new ArrayList<TripleExtend>();

    public AdjacentListGraphOp(GraphType kind, String[] verTexs, TripleExtend[] triples) {
        super(kind, verTexs, triples);
    }

    /**
     * 深度遍历图转二叉树
     *
     * @return
     */
    public BinaryTreeImpl dfsForest() {
        // 生成空树
        BinaryTreeImpl tree = new BinaryTreeImpl();
        //初始化isVisited
        isVisited = new boolean[vexNum];
        // 设置pre
        BinaryTreeNode pre = tree.node;
        BinaryTreeNode newTree;
        for (int i = 0; i < vexNum; i++) {
            if (!isVisited[i]) {
                newTree = dfsTree(i);
                if (tree.node == null) {
                    tree.node = newTree;
                } else {
                    pre.rChild = newTree;
                }
                pre = newTree;
            }
        }
        return tree;
    }

    /**
     * 深度遍历树
     *
     * @param vexIndex
     * @return
     */
    BinaryTreeNode dfsTree(int vexIndex) {
        BinaryTreeNode node = new BinaryTreeNode(vexs[vexIndex].data);
        BinaryTreeNode pre = node;
        isVisited[vexIndex] = true;
        boolean first = true;
        AdjacentListArc arcNode = vexs[vexIndex].firstArc;
        while (arcNode != null) {
            if (!isVisited[arcNode.adjVex]) {
                if (first) {
                    node.lChild = dfsTree(arcNode.adjVex);
                    first = false;
                } else {
                    pre.rChild = dfsTree(arcNode.adjVex);
                }
                pre = node;
            }
            arcNode = arcNode.nextArc;
        }
        return node;
    }

    /**
     * 广度遍历图转二叉树
     *
     * @return
     */
    public BinaryTreeImpl bfsForest() {
        // 生成空树
        BinaryTreeImpl tree = new BinaryTreeImpl();
        //初始化isVisited
        isVisited = new boolean[vexNum];
        // 设置pre
        BinaryTreeNode newNode;
        BinaryTreeNode pre = tree.node;
        for (int i = 0; i < vexNum; i++) {
            if (!isVisited[i]) {
                newNode = bfsTree(i);
                if (tree.node == null) {
                    tree.node = newNode;
                } else {
                    pre.rChild = newNode;
                }
                pre = newNode;
            }
        }
        return tree;
    }

    public BinaryTreeNode bfsTree(int vexIndex) {
        BinaryTreeNode newNode = new BinaryTreeNode(vexs[vexIndex].data);
        BinaryTreeNode pre = newNode;
        isVisited[vexIndex] = true;
        //结点入队
        queue.enQueue(newNode);
        while (!queue.queueEmpty()) {
            boolean first = true;
            //结点出队
            BinaryTreeNode node = (BinaryTreeNode) queue.deQueue();
            //取出该结点对应的顶点的邻接点
            AdjacentListArc arcNode = vexs[locateVex(node.data)].firstArc;
            while (arcNode != null) {
                if (!isVisited[arcNode.adjVex]) {
                    isVisited[arcNode.adjVex] = true;
                    //生成一个孩子结点
                    BinaryTreeNode child = new BinaryTreeNode(vexs[arcNode.adjVex].data);
                    if (first) {
                        node.lChild = child;
                        first = false;
                    } else {
                        pre.rChild = child;
                    }
                    pre = child;
                    //入队：
                    queue.enQueue(child);
                }
                arcNode = arcNode.nextArc;
            }
        }
        return newNode;
    }

    /**
     * 求关节点
     * 1.什么是关节点？如果删除一个顶点以及相关的边，将图的一个连通分量变成多个，则该顶点成为关节点
     * 2.什么是重连通图？一个没有关节点的连通图
     * 5.求关节点的流程、
     * 1. 求根是不是关节点，先深度遍历根的第一个孩子，如果count小于总顶点树，说明根是关节点
     */

    public void findArticul() {
        //1.初始化visit数组,low数组
        visit = new int[vexNum];
        low = new int[vexNum];
        visit[0] = 1;
        visit(vexs[0].data);
        for (int i = 1; i < vexNum; i++) {
            visit[i] = 0;
        }
        //2.获取第一个顶点的第一条边和第一个邻接点
        AdjacentListArc arc = vexs[0].firstArc;
        int index = arc.adjVex;
        //3.深度遍历第一个孩子
        dfsArticul(index);
        if (count < vexNum) {
            //说明根结点是关结点
            vexName.add((String) vexs[0].data);
            //深度优先遍历其他的邻接点
            while (arc.nextArc != null) {
                arc = arc.nextArc;
                index = arc.adjVex;
                if (visit[index] == 0) {
                    dfsArticul(index);
                }
            }
        }
    }

    /**
     * 计算当前顶点的visit和low
     *
     * @param index
     */
    public void dfsArticul(int index) {
        int min = 0;
        //min为visit[index]和low[index的孩子]和visit[祖先]的最小值
        visit[index] = min = ++count;
        visit(vexs[index].data);
        AdjacentListArc arc = vexs[index].firstArc;
        while (arc != null) {
            int arcIndex = arc.adjVex;
            //如果孩子结点没有被访问过
            if (visit[arcIndex] == 0) {
                dfsArticul(arcIndex);
                if (low[arcIndex] < min) {
                    min = low[arcIndex];
                }
                if (low[arcIndex] >= visit[index]) {
                    if (!vexName.contains((String) vexs[index].data)) {
                        vexName.add((String) vexs[index].data);
                    }

                }
            } else if (visit[arcIndex] < min) {
                min = visit[arcIndex];
            }
            arc = arc.nextArc;
        }
        low[index] = min;
    }

    /**
     * 拓扑排序：找一个入度为0的顶点开始，删除，并把和这个顶点相关的弧全部删除，然后重复前面的步骤，直到不存在入度为0的顶点
     */
    public void topologicalSort() {
        //记录访问个数
        int count = 0;
        //1.求每个顶点的入度，并存入数组,并将入度为0的顶点下标入栈
        findInDegree();
        for (int i = 0; i < vexNum; i++) {
            if (inDegreeArray[i] == 0) {
                stack.push(i);
            }
        }
        //2.若栈不为空，则从出栈一个，访问他后，将其删除(也就是以其为入度的顶点，记录的入度个数减一)
        while (!stack.stackEmpty()) {
            Integer index = (Integer) stack.pop();
            vexName.add((String) vexs[index].data);
            count++;
            AdjacentListArc arc = vexs[index].firstArc;
            while (arc != null) {
                inDegreeArray[arc.adjVex]--;
                if (inDegreeArray[arc.adjVex] == 0) {
                    stack.push(arc.adjVex);
                }
                arc = arc.nextArc;
            }
        }
        //3.判断访问个数和顶点个数的大小，如果count小于顶点个数，则说明有环
        if (count < vexNum) {
            throw new RuntimeException("有向图有环");
        }
    }

    /**
     * 求关键路径
     */
    public void criticalPath (){
        //1.拓扑排序求ve
        toPoLogicalOrder();
        vl = new int[vexNum];
        //2.逆拓扑排序求vl，顶点的最晚发生时间
        for (int i = 0; i < vexNum; i++) {
            vl[i] = ve[(Integer) toPoStack.getTop()];
        }

        while (!toPoStack.stackEmpty()) {
            Integer index = (Integer) toPoStack.pop();
            AdjacentListArc arc = vexs[index].firstArc;
            while (arc != null) {
                if (vl[index] > vl[arc.adjVex] - arc.info) {
                    vl[index] = vl[arc.adjVex] - arc.info;
                }
                arc = arc.nextArc;
            }
        }
        //3.求e（弧的最早开始时间）和l（弧的最迟开始时间）,并输出关键路径
        for (int i = 0; i < vexNum; i++) {
            AdjacentListArc arc = vexs[i].firstArc;
            while (arc != null) {
                int e = ve[i];
                int l = vl[arc.adjVex] - arc.info;
                if (e == l) {
                    TripleExtend tripleExtend = new TripleExtend(vexs[i].data, vexs[arc.adjVex].data , arc.info);
                    edgeList.add(tripleExtend);
                }
                arc = arc.nextArc;
            }
        }
    }

    public void toPoLogicalOrder() {
        int count = 0;
        stack.clearStack();
        ve = new int[vexNum];
        //1.需要一个保存度为0的栈和一个存拓扑排序的栈
        findInDegree();
        for (int i = 0; i < vexNum; i++) {
            if (inDegreeArray[i] == 0) {
                stack.push(i);
            }
        }
        //2.拓扑排序求ve,求顶点的最早发生时间
        while (!stack.stackEmpty()) {
            Integer index = (Integer) stack.pop();
            toPoStack.push(index);
            count++;
            AdjacentListArc arc = vexs[index].firstArc;
            while (arc != null) {
                if (--inDegreeArray[arc.adjVex] == 0){
                    stack.push(arc.adjVex);
                }
                if (ve[arc.adjVex] < arc.info + ve[index]) {
                    ve[arc.adjVex] = arc.info + ve[index];
                }
                arc = arc.nextArc;
            }
        }
        if (count < vexNum) {
            throw new RuntimeException("有向图有环");
        }
    }

    /**
     * 计算每个顶点的入度
     */
    void findInDegree() {
        inDegreeArray = new int[vexNum];
        for (int i = 0; i < vexNum; i++) {
            AdjacentListArc arc = vexs[i].firstArc;
            while (arc != null) {
                inDegreeArray[arc.adjVex]++;
                arc = arc.nextArc;
            }
        }
    }
}
