package com.data.structure.matrix.impl;

import com.data.structure.matrix.SparseMatrix;
import com.data.structure.matrix.model.OlNode;

/**
 * OrthogonalListSparseMatrix:
 * 十字链表
 * @author sunchen
 * @date 2020/5/21 11:22 上午
 */
public class OrthogonalListSparseMatrix implements SparseMatrix<OrthogonalListSparseMatrix> , Cloneable{
    OlNode[] rHead;
    OlNode[] cHead;
    int mu, nu, tu;

    public OrthogonalListSparseMatrix(){}

    public OrthogonalListSparseMatrix(int[][] a){
        mu = a.length;
        nu = a[0].length;
        rHead = new OlNode[mu+1];
        cHead = new OlNode[nu+1];

        for (int row = mu; row > 0; row--) {
            for (int col = nu; col > 0; col--) {
                int data = a[row-1][col-1];
                if (data != 0) {
                    tu++;
                    //头插行表
                    OlNode olNode = new OlNode(row, col, data);
                    olNode.right = rHead[row];
                    rHead[row] = olNode;
                    //头插列表
                    olNode.down = cHead[col];
                    cHead[col] = olNode;
                }
            }
        }
    }

    @Override
    public Object[][] toArray() {
        Object[][] result = new Object[mu][nu];
        for (int row = 1; row <= mu; row++) {
            OlNode node = rHead[row];
            while (node != null) {
                result[node.row-1][node.col-1] = node.e;
                node = node.right;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object[][] a = toArray();
        for (int row = 0; row < mu; row++) {
            for (int col = 0; col < nu; col++) {
                if (a[row][col] == null) {
                    a[row][col] = 0;
                }
                sb.append(a[row][col]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public OrthogonalListSparseMatrix addSpareMatrix(OrthogonalListSparseMatrix orthogonalListSparseMatrix) {
        return addOrSub(orthogonalListSparseMatrix, 0);
    }

    @Override
    public OrthogonalListSparseMatrix subSpareMatrix(OrthogonalListSparseMatrix orthogonalListSparseMatrix) {
        return addOrSub(orthogonalListSparseMatrix, 1);
    }

    public OrthogonalListSparseMatrix addOrSub(OrthogonalListSparseMatrix m2, int type){
        if (m2 == null || m2.mu == 0 || m2.nu == 0 || m2.mu != mu || m2.nu != nu) {
            throw new RuntimeException("不满足计算条件");
        }
        for (int row = 1; row <= mu; row++) {
            //矩阵1 第row行第一个非零元
            OlNode start1 = rHead[row];
            //矩阵2 第row行第一个非零元
            OlNode start2 = m2.rHead[row];
            OlNode pre = null;
            //遍历矩阵1和矩阵2第row行的所有非零元
            while (start1 != null && start2 != null) {
                //当前矩阵1非零元的列
                int col1 = start1.col;
                //当前矩阵2非零元的列
                int col2 = start2.col;
                //往这个结点的左边插入
                if (col1 > col2) {
                    int data = type == 1 ? (-1)*start2.e : start2.e;
                    OlNode newNode = new OlNode(row, col2, data);
                    insAfter(pre, newNode);
                    pre = newNode;
                    start2 = start2.right;
                    tu++;
                } else if (col1 < col2) {
                    pre = start1;
                    start1 = start1.right;
                    tu++;
                } else {
                    int data1 = start1.e;
                    int data2 = type == 1 ? (-1)*start2.e : start2.e;
                    int data = data1 + data2;
                    if (data == 0) {
                        //从pre后面删除一个元素
                        deleteAfter(pre, start1);
                        tu--;
                    } else{
                        start1.e = data;
                        pre = start1;
                    }
                    start1 = start1.right;
                    start2 = start2.right;
                }
            }
            while (start2 != null) {
                int col2 = start2.col;
                int row2 = start2.row;
                int data = type == 1 ? (-1)*start2.e : start2.e;
                OlNode newNode = new OlNode(row2, col2, data);
                insAfter(pre, newNode);
                pre = newNode;
                tu++;
                start2 = start2.right;
            }
        }
        return this;
    }

    @Override
    public OrthogonalListSparseMatrix multSpareMatrix(OrthogonalListSparseMatrix m2) {
        OrthogonalListSparseMatrix result = new OrthogonalListSparseMatrix();
        result.mu = mu;
        result.nu = m2.nu;
        result.rHead = new OlNode[result.mu+1];
        result.cHead = new OlNode[result.nu+1];
        for (int row = 1; row <= mu; row++) {
            OlNode pre = null;
            for (int col = 1; col <= nu; col++) {
                //取出矩阵1row行的非零元
                OlNode rStart = rHead[row];
                //取出矩阵2col行的非零元
                OlNode cStart = m2.cHead[col];
                int data = 0;
                while (rStart != null && cStart != null){
                    int col1 = rStart.col;
                    int row2 = cStart.row;
                    if (col1 < row2) {
                        rStart = rStart.right;
                    } else if (col1 > row2) {
                        cStart = cStart.down;
                    } else {
                        data += rStart.e * cStart.e;
                        rStart = rStart.right;
                        cStart = cStart.down;
                    }
                }

                if (data != 0) {
                    OlNode newNode = new OlNode(row, col, data);
                    result.insAfter(pre, newNode);
                    pre = newNode;
                    result.tu++;
                }
            }
        }
        return result;
    }

    @Override
    public OrthogonalListSparseMatrix transposeSpareMatrix() {
        OrthogonalListSparseMatrix transPose = new OrthogonalListSparseMatrix();
        //设置基本项
        transPose.mu = nu;
        transPose.nu = mu;
        transPose.tu = tu;
        transPose.rHead = new OlNode[nu+1];
        transPose.cHead = new OlNode[mu+1];
        for (int col = 1; col <= nu; col++) {
            OlNode pre = null;
            OlNode cNode = cHead[col];
            while (cNode != null) {
                OlNode newNode = new OlNode(col, cNode.row, cNode.e);
                transPose.insAfter(pre, newNode);
                pre = newNode;
                cNode = cNode.down;
            }
        }
        return transPose;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OrthogonalListSparseMatrix clone = (OrthogonalListSparseMatrix) super.clone();
        clone.mu = mu;
        clone.nu = nu;
        clone.tu = tu;
        System.arraycopy(rHead, 0, clone.rHead, 0, rHead.length);
        System.arraycopy(cHead, 0, clone.cHead, 0, cHead.length);
        return clone;
    }

    /**
     * 往pre结点后面插入一个结点
     * @param pre 要插入结点的前一个
     * @param newNode 要插入的结点
     */
    public void insAfter (OlNode pre, OlNode newNode) {
        //建立行关系
        int row = newNode.row;
        if (pre == null) {
            newNode.right = rHead[row];
            rHead[row] = newNode;
        } else {
            newNode.right = pre.right;
            pre.right = newNode;
        }
        //建立列关系
        int col = newNode.col;
        OlNode start = cHead[col];
        if (start == null || start.col > col) {
            newNode.down = cHead[col];
            cHead[col] = newNode;
        } else {
            while (start.down != null && start.down.col < col){
                start = start.down;
            }
            newNode.down = start.down;
            start.down = newNode;
        }
    }

    /**
     * 删除pre结点后面的一个结点
     * @param pre 要删除结点的前一个
     * @param newNode 要删除的结点
     */
    public void deleteAfter(OlNode pre, OlNode newNode){
        //建立行关系
        int row = newNode.row;
        if (pre == null) {
            rHead[row] = rHead[row].right;
        } else {
            pre.right = newNode.right;
        }
        //建立列关系
        int col = newNode.col;
        OlNode start = cHead[col];
        while (start.down != null && start.down.col < col){
            start = start.down;
        }
        start.right = newNode.right;
    }
}
