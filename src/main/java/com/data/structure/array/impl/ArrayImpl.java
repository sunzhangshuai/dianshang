package com.data.structure.array.impl;

import com.data.structure.array.Array;

/**
 * @author sunchen
 */
public class ArrayImpl implements Array {

    int[] bounds;

    int[] constants;

    Object[] base;

    int dim;

    public ArrayImpl(int... n) {
        checkDim(n);
        dim = n.length;
        // 判断合法性
        int elementTotal = 1;
        bounds = new int[dim];
        for (int i = 0; i < dim; i++) {
            bounds[i] = n[i];
            elementTotal = elementTotal * bounds[i];
        }
        constants = new int[dim];
        constants[dim - 1] = 1;
        int max = dim - 2;
        for (int i = max; i >= 0; i--) {
            constants[i] = constants[i + 1] * bounds[i + 1];
        }
        base = new Object[elementTotal];
    }

    @Override
    public void destroyArray() {
        for (int i = 0; i < dim; i++) {
            base[i] = null;
        }
        bounds = null;
        constants = null;
        base = null;
        dim = 0;
    }

    @Override
    public Object value(int... n) {
        checkDim(n);
        return base[locate(n)];
    }

    @Override
    public void assign(Object e, int... n) {
        checkDim(n);
        base[locate(n)] = e;
    }

    @Override
    public int[][] toArray() {
        // todo
        return new int[0][];
    }

    /**
     * 根据下标获取位置
     *
     * @param n 下标
     * @return 位置
     */
    public int locate(int... n) {
        int offSet = 0;
        for (int i = 0; i < dim; i++) {
            offSet += n[i] * constants[i];
        }
        return offSet;
    }

    /**
     * 验证下标合法性
     *
     * @param n 下标
     */
    void checkDim(int... n) {
        for (int value : n) {
            if (value < 0) {
                throw new RuntimeException("dim 不合法");
            }
        }
    }
}
