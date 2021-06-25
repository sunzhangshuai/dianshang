package com.data.structure.sort;

/**
 * 冒泡排序
 */
public class BubbleSort extends Sort{
    public void sort (int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean change = true;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j-1] > array[j]) {
                    change = false;
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
            if (change) {
                break;
            }
        }
    }
}
