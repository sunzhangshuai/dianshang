package com.data.algorithm.practice.middle;

/**
 * Path:
 *
 * @author sunchen
 * @date 2020/7/6 9:03 下午
 */
public class Path63 {

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * * 0 0 0 0 0 0
     * * 0 1 0 0 1 0
     * * 0 1 0 0 0 0
     * * 0 0 0 1 0 0
     *
     * @return
     */
    public static int pathNum(int row, int col, int a[][]) {
        if (row == 1 && col == 1) {
            return 1;
        }
        if (row < 1 || col < 1) {
            return 0;
        }
        if (a[row - 1][col - 1] == 1) {
            return 0;
        }
        return pathNum(row, col - 1, a) + pathNum(row - 1, col, a);
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{
                new int[]{0, 0, 0, 0, 0, 0},
                new int[]{0, 1, 0, 0, 1, 0},
                new int[]{0, 1, 0, 0, 0, 0},
                new int[]{0, 0, 0, 1, 0, 0}

        };
        int sum = pathNum(4, 6, array);
        System.out.println(sum);
    }
}
