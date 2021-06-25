package com.data.structure.sort;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/22 8:08 下午
 */
public class Test {
    public static void main(String[] args) {
        Sort sort = new MergeSort();
        int[] array = new int[]{2, 5, 1, 8, 3, 9, 2};
        sort.sort(array, 0, 6);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
