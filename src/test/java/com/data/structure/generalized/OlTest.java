package com.data.structure.generalized;

import com.data.structure.matrix.impl.OrthogonalListSparseMatrix;
import org.junit.Before;
import org.junit.Test;

/**
 * 十字链表
 */
public class OlTest {

    OrthogonalListSparseMatrix m1;

    OrthogonalListSparseMatrix m2;

    OrthogonalListSparseMatrix m3;

    @Before
    public void init(){
        // 0 0 1
        // 0 1 1
        // 0 0 1
        // 0 0 0
        int[][] a = new int[4][3];
        a[0][2] = 1; // 0 1 2 4 5
        a[1][1] = 1;
        a[1][2] = 1;
        a[2][2] = 1;
        m1 = new OrthogonalListSparseMatrix(a);
        int[][] a2 = new int[3][4];
        // 0 0 1 0       0 1 3
        // 1 1 1 0
        // 0 1 0 0
        a2[0][2] = 1;
        a2[1][0] = 1;
        a2[1][1] = 1;
        a2[1][2] = 1;
        a2[2][1] = 1;
        m2 = new OrthogonalListSparseMatrix(a2);

        // 0 0 1
        // 0 -1 0
        // 0 0 1
        // 0 0 1
        int[][] a3 = new int[4][3];
        a3[0][2] = 1; // 0 1 2 4 5
        a3[1][1] = -1;
        a3[2][2] = 1;
        a3[3][2] = 1;
        m3 = new OrthogonalListSparseMatrix(a3);
    }

    /**
     * 相加
     */
    @Test
    public void add(){
        // 0 0 2
        // 0 0 1
        // 0 0 2
        // 0 0 1
        m1.addSpareMatrix(m3);
        System.out.println(m1.toString());
    }

    /**
     * 相减
     */
    @Test
    public void sub(){
        // 0 0 0
        // 0 2 1
        // 0 0 0
        // 0 0 -1
        m1.subSpareMatrix(m3);
        System.out.println(m1.toString());
    }

    /**
     * 相乘
     */
    @Test
    public void mult(){
        OrthogonalListSparseMatrix mult = m1.multSpareMatrix(m2);
        System.out.println(mult.toString());
    }

    /**
     * 转置
     */
    @Test
    public void transport(){
        //0 0 0 0
        //0 1 0 0
        //1 1 1 0
        System.out.println(m1.transposeSpareMatrix().toString());
    }

    @Test
    public void copy() throws CloneNotSupportedException {
        // 0 0 1
        // 0 1 1
        // 0 0 1
        // 0 0 0
        System.out.println(m1.clone().toString());
    }
}
