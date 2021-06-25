package com.data.structure.array;

/**
 * @author sunchen
 */
public interface Array {

    /**
     * 销毁数组
     */
    void destroyArray();

    /**
     * 取值
     * @param n 数组每阶的下标
     * @return 值
     */
    Object value(int ...n);

    /**
     * 赋值
     * @param e 值
     * @param n 数组每阶的下标
     */
    void assign(Object e, int ...n);

    /**
     * 转成二维数组
     * @return 二维数组
     */
    int[][] toArray();
}
