package com.data.structure.matrix;

import com.data.structure.matrix.impl.SequenceSparseMatrix;
import org.junit.Before;
import org.junit.Test;

public class RLSparseMatrixImplTest {

    SequenceSparseMatrix rlSparseMatrix1;

    SequenceSparseMatrix rlSparseMatrix2;

    @Before
    public void init(){
        // 0 0 1
        // 0 1 1
        // 0 0 1
        // 0 0 0
        Object[][] a = new Object[4][3];
        a[0][2] = 1; // 0 1 2 4 5
        a[1][1] = 1;
        a[1][2] = 1;
        a[2][2] = 1;
        rlSparseMatrix1 = new SequenceSparseMatrix(a);
        Object[][] a2 = new Object[3][4];
        // 0 0 1 0       0 1 3
        // 1 1 1 0
        // 0 1 0 0
        a2[0][2] = 1;
        a2[1][0] = 1;
        a2[1][1] = 1;
        a2[1][2] = 1;
        a2[2][1] = 1;
        rlSparseMatrix2 = new SequenceSparseMatrix(a2);
        //0 1 0 0
        //1 2 1 0
        //0 1 0 0
        //0 0 0 0
    }

    /**
     * 相加
     */
    @Test
    public void add(){
        SequenceSparseMatrix add = rlSparseMatrix1.addSpareMatrix(rlSparseMatrix2);
        System.out.println(add.toString());
    }

    /**
     * 相减
     */
    @Test
    public void sub(){
        SequenceSparseMatrix sub = rlSparseMatrix1.subSpareMatrix(rlSparseMatrix2);
        System.out.println(sub.toString());
    }

    /**
     * 相乘
     */
    @Test
    public void mult(){
        System.out.println(rlSparseMatrix2.toString());
        System.out.println(rlSparseMatrix1.toString());
        SequenceSparseMatrix mult = rlSparseMatrix1.multSpareMatrix(rlSparseMatrix2);
        System.out.println(mult.toString());
    }

    /**
     * 转置
     */
    @Test
    public void transport(){
        System.out.println(rlSparseMatrix1.transposeSpareMatrix().toString());
    }
}
