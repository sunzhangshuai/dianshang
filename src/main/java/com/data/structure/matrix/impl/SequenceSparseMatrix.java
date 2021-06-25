package com.data.structure.matrix.impl;

import com.data.structure.matrix.SparseMatrix;
import com.data.structure.matrix.model.Triple;

import java.io.Serializable;

/**
 * @author sunchen
 */
public class SequenceSparseMatrix implements SparseMatrix<SequenceSparseMatrix>, Cloneable, Serializable {

    /**
     * 非零元的最大个数
     */
    private static final int MAXSIZE = 12500;

    /**
     * 非零元三元组
     */
    Triple[] data;

    /**
     * 各行起始下标
     */
    int[] rpos;

    /**
     * 行数，列数，非零元个数
     */
    int mu, nu, tu;

    public SequenceSparseMatrix() {
    }

    public SequenceSparseMatrix(Object[][] a) {
        int row = a.length;
        mu = row;
        int col = a[0].length;
        nu = col;
        int size = Math.min(mu * nu, MAXSIZE);
        data = new Triple[size];

        rpos = new int[mu + 1];
        int index = 1;
        for (int i = 0; i < row; i++) {
            rpos[i + 1] = index;
            for (int j = 0; j < col; j++) {
                if (a[i][j] != null) {
                    data[index] = new Triple(i + 1, j + 1, a[i][j]);
                    index++;
                }
            }
        }
        tu = index - 1;
    }

    @Override
    public Object[][] toArray() {
        Object[][] a = new Object[mu][nu];
        for (int k = 1; k <= tu; k++) {
            int i = data[k].row - 1;
            int j = data[k].column - 1;
            a[i][j] = data[k].e;
        }
        return a;
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
    public SequenceSparseMatrix clone() throws CloneNotSupportedException {
        SequenceSparseMatrix rlSparseMatrix = (SequenceSparseMatrix) super.clone();
        rlSparseMatrix.mu = this.mu;
        rlSparseMatrix.nu = this.nu;
        rlSparseMatrix.tu = this.tu;
        System.arraycopy(data, 1, rlSparseMatrix.data, 1, tu);
        System.arraycopy(rpos, 1, rlSparseMatrix.rpos, 1, mu);
        return rlSparseMatrix;
    }

    @Override
    public SequenceSparseMatrix addSpareMatrix(SequenceSparseMatrix sparseMatrix) {
        if (sparseMatrix == null || sparseMatrix.mu == 0 || sparseMatrix.nu == 0 || sparseMatrix.mu != mu || sparseMatrix.nu != nu) {
            throw new RuntimeException("不满足相加的条件");
        }
        return addOrSub(sparseMatrix, 0);
    }

    @Override
    public SequenceSparseMatrix subSpareMatrix(SequenceSparseMatrix sparseMatrix) {
        if (sparseMatrix == null || sparseMatrix.mu == 0 || sparseMatrix.nu == 0 || sparseMatrix.mu != mu || sparseMatrix.nu != nu) {
            throw new RuntimeException("不满足相减的条件");
        }
        return addOrSub(sparseMatrix, 1);
    }

    /**
     * 矩阵加（减）运算
     *
     * @param sparseMatrix 参与运算的矩阵2
     * @param type         1-减 0-加
     * @return 运算结果
     */
    public SequenceSparseMatrix addOrSub(SequenceSparseMatrix sparseMatrix, int type) {
        //设置结果矩阵的基本项
        SequenceSparseMatrix result = new SequenceSparseMatrix();
        result.mu = mu;
        result.nu = nu;
        int size = Math.min(result.mu * result.nu, MAXSIZE);
        result.data = new Triple[size];
        result.rpos = new int[result.mu + 1];

        int index = 1;
        //取两个矩阵每一行的非零元进行相加
        for (int row1 = 1; row1 <= mu; row1++) {
            //设置每一行的第一个非零元的位置
            result.rpos[row1] = index;
            int start1 = rpos[row1];
            int end1 = row1 == mu ? tu : rpos[row1 + 1] - 1;
            int start2 = sparseMatrix.rpos[row1];
            int end2 = row1 == mu ? sparseMatrix.tu : sparseMatrix.rpos[row1 + 1] - 1;
            while (start1 <= end1 && start2 <= end2) {
                int col1 = data[start1].column;
                int data1 = (Integer) data[start1].e;
                int col2 = sparseMatrix.data[start2].column;
                int data2 = type == 1 ? (-1) * (Integer) sparseMatrix.data[start2].e : (Integer) sparseMatrix.data[start2].e;
                if (col1 < col2) {
                    result.data[index] = new Triple(row1, col1, data1);
                    index++;
                    start1++;
                } else if (col1 == col2) {
                    int sum = data1 + data2;
                    if (sum != 0) {
                        result.data[index] = new Triple(row1, col1, sum);
                        index++;
                    }
                    start1++;
                    start2++;
                } else {
                    result.data[index] = new Triple(row1, col2, data2);
                    index++;
                    start2++;
                }
            }
            //谁没走完，续谁
            while (start1 <= end1) {
                int data1 = (Integer) data[start1].e;
                result.data[index] = new Triple(row1, data[start1].column, data1);
                start1++;
                index++;
            }

            while (start2 <= end2) {
                int data2 = type == 1 ? (-1) * (Integer) sparseMatrix.data[start2].e : (Integer) sparseMatrix.data[start2].e;
                result.data[index] = new Triple(sparseMatrix.data[start2].row, sparseMatrix.data[start2].column, data2);
                start2++;
                index++;
            }
        }
        //设置非零元的个数
        result.tu = index - 1;
        return result;
    }

    @Override
    public SequenceSparseMatrix multSpareMatrix(SequenceSparseMatrix sparseMatrix) {
        int[] sum;

        // 1.设置结果的基本项
        SequenceSparseMatrix result = new SequenceSparseMatrix();
        result.mu = mu;
        result.nu = sparseMatrix.nu;
        int size = Math.min(result.mu * result.nu, MAXSIZE);
        result.data = new Triple[size];
        result.rpos = new int[result.mu + 1];

        int index = 1;
        // 遍历矩阵1每一行
        for (int row1 = 1; row1 <= mu; row1++) {
            // 设置每行开始下标
            result.rpos[row1] = index;
            // 初始化新矩阵当前行（row1）的结果集
            sum = new int[sparseMatrix.nu + 1];

            int start1 = rpos[row1];
            int end1 = row1 == mu ? tu : rpos[row1 + 1] - 1;
            // 用当前行每一个数的列，去找矩阵2与之相等的行，然后与矩阵2行的每一个数相乘，按照矩阵2的列，将乘积放入结果集中
            while (start1 <= end1) {
                int col1 = data[start1].column;

                int start2 = sparseMatrix.rpos[col1];
                int end2 = col1 == sparseMatrix.mu ? sparseMatrix.tu : sparseMatrix.rpos[col1 + 1] - 1;
                while (start2 <= end2) {
                    int col2 = sparseMatrix.data[start2].column;
                    // 若结果集中已经有该列的数据，则累加
                    sum[col2] += (Integer) sparseMatrix.data[start2].e;
                    start2++;
                }
                start1++;
            }

            // 遍历当前行的结果集，把不为空的，设置到结果矩阵的data中
            for (int col = 1; col <= result.nu; col++) {
                if (sum[col] != 0) {
                    result.data[index] = new Triple(row1, col, sum[col]);
                    result.tu++;
                    index++;
                }
            }
        }
        return result;
    }

    @Override
    public SequenceSparseMatrix transposeSpareMatrix() {
        SequenceSparseMatrix sparseMatrix = new SequenceSparseMatrix();
        // 设置转置矩阵的基本项
        sparseMatrix.mu = nu;
        sparseMatrix.nu = mu;
        sparseMatrix.tu = tu;
        int size = Math.min(sparseMatrix.mu * sparseMatrix.nu, MAXSIZE);
        sparseMatrix.data = new Triple[size];
        sparseMatrix.rpos = new int[sparseMatrix.mu + 1];
        // 设置一个count数组，用于存储每列有多少个非零元
        int[] count = new int[sparseMatrix.mu + 1];
        for (int i = 1; i <= tu; i++) {
            count[data[i].column]++;
        }
        //计算转置矩阵每一行的第一个非零元
        sparseMatrix.rpos[1] = 1;
        for (int i = 2; i <= sparseMatrix.mu; i++) {
            sparseMatrix.rpos[i] = sparseMatrix.rpos[i - 1] + count[i - 1];
        }
        //设置转置矩阵的data
        for (int i = 1; i <= tu; i++) {
            int col = data[i].column;
            sparseMatrix.data[sparseMatrix.rpos[col]] = new Triple(col, data[i].row, data[i].e);
            sparseMatrix.rpos[col]++;
        }
        return sparseMatrix;
    }
}
