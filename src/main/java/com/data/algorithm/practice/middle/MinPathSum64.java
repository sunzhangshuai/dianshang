package com.data.algorithm.practice.middle;

import java.util.HashMap;

/**
 * MinPathSum64:
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 *
 * @author sunchen
 * @date 2020/7/23 6:59 下午
 */
public class MinPathSum64 {
    public static void main(String[] args) {
        int[][] a = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int result = handler(a);
        System.out.println(result);
    }

    public static int handler(int[][] grid) {
        return getResult(grid, 3, 3);
    }
    static HashMap<String, Integer> cache= new HashMap<>();
    public static int getResult(int[][] grid, int row, int col) {

        if (row < 1 || col < 1) {
            return Integer.MAX_VALUE;
        }
        if (row == 1 && col == 1) {
            return grid[0][0];
        }
        String key = row + "-" + col;
        if (!cache.containsKey(key)) {
            int result = grid[row - 1][col - 1] + Math.min(getResult(grid, row - 1, col), getResult(grid, row, col - 1));
            cache.put(key, result);
        }
        return cache.get(key);
    }
}
