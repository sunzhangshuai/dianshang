package com.data.structure.matrix;

/**
 * @author sunchen
 */
public interface SparseMatrix<T> {
    /**
     * 输出稀疏矩阵
     *
     * @return
     */
    Object[][] toArray();

    /**
     * 求稀疏矩阵的和放入q中
     *
     * @param t
     * @return
     */
    T addSpareMatrix(T t);

    /**
     * 求稀疏矩阵的差放入q中
     *
     * @param t
     * @return
     */
    T subSpareMatrix(T t);

    /**
     * 稀疏矩阵乘
     *
     * @param t
     * @return
     */
    T multSpareMatrix(T t);

    /**
     * 转置存入t中
     * @return
     */
    T transposeSpareMatrix();
}
