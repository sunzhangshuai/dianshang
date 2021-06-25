package com.data.algorithm.practice.primary;

/**
 * TestArray:
 *
 * @author sunchen
 * @date 2020/8/15 7:40 下午
 */
public class TestArray {
    public static void main(String[] args) {
        int a[][] = new int[5][5];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = i+j;
            }
        }
        a[0] = a[1];

        a[0][1] = 30;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.println(a[i][j]);
            }
        }
    }
}
