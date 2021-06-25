package com.data.structure.graph.impl;

import com.data.structure.graph.model.TripleExtend;
import com.data.structure.graph.model.arc.OrthogonalListArc;
import com.data.structure.graph.model.vertex.OrthogonalListVerTex;

import java.util.ArrayList;

/**
 * OrthogonalListGraphImpl:
 * 十字链表（有向图）
 *
 * @author sunchen
 * @date 2020/6/1 3:57 下午
 */
public class OrthogonalListGraphImpl extends AbstractGraph<OrthogonalListVerTex> {

    public OrthogonalListGraphImpl(String[] vexNames, TripleExtend[] triples) {
        this.vexs = new OrthogonalListVerTex[MAX_SIZE];
        create(vexNames, triples);
    }

    @Override
    public Object firstAdjVex(Object vexName) {
        int index = locateVex(vexName);
        OrthogonalListArc firstOut = vexs[index].firstOut;
        if (firstOut != null) {
            return vexs[firstOut.headVex].data;
        }
        return null;
    }

    @Override
    public Object nextAdjVex(Object vexName, Object nextVexName) {
        int index = locateVex(vexName);
        int nextVexIndex = locateVex(nextVexName);
        OrthogonalListArc firstOut = vexs[index].firstOut;
        while (firstOut != null && firstOut.headVex != nextVexIndex) {
            firstOut = firstOut.tLink;
        }
        if (firstOut != null && firstOut.tLink != null) {
            return vexs[firstOut.tLink.headVex].data;
        }
        return null;
    }

    @Override
    public void insertVex(Object vexName) {
        if (vexNum == MAX_SIZE) {
            throw new RuntimeException("顶点已满");
        }
        vexs[vexNum] = new OrthogonalListVerTex(vexName);
        vexNum++;
    }

    /**
     * 删除顶点
     *
     * @param vexName
     */
    @Override
    public void deleteVex(Object vexName) {
        //1.计算顶点在数组中的位置
        int index = locateVex(vexName);
        //2.删除以顶点为出度的弧关系
        OrthogonalListArc firstOut = vexs[index].firstOut;
        while (firstOut != null) {
            deleteArc(vexName, vexs[firstOut.headVex].data);
            firstOut = firstOut.tLink;
        }
        //3.删除以顶点为入度的弧关系
        OrthogonalListArc firstIn = vexs[index].firstIn;
        while (firstIn != null) {
            //删除弧关系
            deleteArc(vexs[firstIn.tailVex].data, vexName);
            firstIn = firstIn.hLink;
        }
        //4.移动删除该结点后，后面的结点
        if (vexNum - 1 - index >= 0) {
            System.arraycopy(vexs, index + 1, vexs, index, vexNum - 1 - index);
        }
        vexNum--;
        //5.修改需要修改的弧结点的弧尾和弧头下标
        for (int i = 0; i < vexNum; i++) {
            OrthogonalListArc firstOutNode = vexs[i].firstOut;
            while (firstOutNode != null) {
                if (firstOutNode.tailVex > index) {
                    firstOutNode.tailVex--;
                }
                if (firstOutNode.headVex > index) {
                    firstOutNode.headVex--;
                }
                firstOutNode = firstOutNode.tLink;
            }
        }
    }

    /**
     * 新增一个弧结点
     *
     * @param data
     */
    @Override
    public void insertArc(TripleExtend data) {
        Object head = data.arcHead;
        Object tail = data.arcTail;
        //弧尾在顶点数组中的下标
        int tailIndex = locateVex(tail);
        //弧头在顶点数组中的下标
        int headIndex = locateVex(head);
        //建立关系
        OrthogonalListArc arcOutNode = new OrthogonalListArc(tailIndex, headIndex);
        arcOutNode.tLink = vexs[tailIndex].firstOut;
        vexs[tailIndex].firstOut = arcOutNode;
        arcOutNode.hLink = vexs[headIndex].firstIn;
        vexs[headIndex].firstIn = arcOutNode;
        arcNum++;
    }

    /**
     * 删除弧结点
     *
     * @param vexName1
     * @param vexName2
     */
    @Override
    public void deleteArc(Object vexName1, Object vexName2) {
        //弧尾在顶点数组中的下标
        int tailIndex = locateVex(vexName1);
        //弧头在顶点数组中的下标
        int headIndex = locateVex(vexName2);
        //在tailIndex的链表中找vexName2,断开出度关系
        OrthogonalListArc firstOut = vexs[tailIndex].firstOut;
        if (firstOut.headVex == headIndex) {
            vexs[tailIndex].firstOut = firstOut.tLink;
        } else {
            while (firstOut.tLink.headVex != headIndex) {
                firstOut = firstOut.tLink;
            }
            firstOut.tLink = firstOut.tLink.tLink;
        }
        //在headIndex的链表中找vexName1，断开入度关系
        OrthogonalListArc firstIn = vexs[headIndex].firstIn;
        if (firstIn.tailVex == tailIndex) {
            vexs[headIndex].firstIn = firstIn.hLink;
        } else {
            while (firstIn.hLink.tailVex != tailIndex) {
                firstIn = firstIn.hLink;
            }
            firstIn.hLink = firstIn.hLink.hLink;
        }
        arcNum--;
    }

    public void visit(int vexIndex) {
        data.add(vexs[vexIndex].data);
        System.out.println(vexIndex);
    }

    public void clear() {
        data = new ArrayList<Object>();
    }


    @Override
    public Integer[][] toArray() {
        Integer[][] arrayIn = toArrayIn();
        Integer[][] arrayOut = toArrayOut();
        for (int row = 0; row < vexNum; row++) {
            for (int col = 0; col < vexNum; col++) {
                if (!arrayIn[row][col].equals(arrayOut[row][col])) {
                    throw new RuntimeException("入度数组和出度数组不相等");
                }
            }
        }
        return arrayOut;
    }

    public Integer[][] toArrayOut() {
        Integer[][] array = new Integer[vexNum][vexNum];
        for (int row = 0; row < vexNum; row++) {
            for (int col = 0; col < vexNum; col++) {
                array[row][col] = 0;
            }
        }
        for (int i = 0; i < vexNum; i++) {
            OrthogonalListArc firstOut = vexs[i].firstOut;
            while (firstOut != null) {
                array[firstOut.tailVex][firstOut.headVex] = 1;
                firstOut = firstOut.tLink;
            }
        }
        return array;
    }

    public Integer[][] toArrayIn() {
        Integer[][] array = new Integer[vexNum][vexNum];
        for (int row = 0; row < vexNum; row++) {
            for (int col = 0; col < vexNum; col++) {
                array[row][col] = 0;
            }
        }
        for (int i = 0; i < vexNum; i++) {
            OrthogonalListArc firstIn = vexs[i].firstIn;
            while (firstIn != null) {
                array[firstIn.tailVex][firstIn.headVex] = 1;
                firstIn = firstIn.hLink;
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
