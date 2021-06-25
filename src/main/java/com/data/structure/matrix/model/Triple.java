package com.data.structure.matrix.model;

/**
 * 三元组
 * @author laosun
 */
public class Triple {

    public int row, column;

    public Object e;

    public Triple(int i, int j, Object e) {
        this.row = i;
        this.column = j;
        this.e = e;
    }
}
