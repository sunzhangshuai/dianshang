package com.data.algorithm.practice.middle;

import com.data.structure.searchtable.hashtable.hash.MedianSquare;
import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import com.data.structure.searchtable.hashtable.table.DoubleHashingImpl;
import com.data.structure.searchtable.model.Element;

import java.util.HashMap;

/**
 * Path:
 *
 * @author sunchen
 * @date 2020/7/6 9:03 下午
 */
public class Path62 {

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 那么从左上角到右下角将会有多少条不同的路径？
     *
     * @return
     */
    static AbstractHashTable table = new DoubleHashingImpl(MedianSquare.getInstance());

    public static int pathNum(int row, int col) {
        if (row == 1 || col == 1) {
            return 1;
        }
        String key = row + "-" + col;
        if (table.search(key) == null) {
            int result = pathNum(row, col - 1) + pathNum(row - 1, col);
            Element element = new Element(key, String.valueOf(result));
            table.insert(element);
        }
        return Integer.parseInt(table.search(key).value);
    }

    public static void main(String[] args) {
        int sum = pathNum(7, 3);
        System.out.println(sum);
    }
}
