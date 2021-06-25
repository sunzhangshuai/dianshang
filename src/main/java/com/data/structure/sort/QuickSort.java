package com.data.structure.sort;

/**
 * 快速排序
 *
 * @author sunchen
 */
public class QuickSort extends Sort {
    @Override
    public void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = part(array, start, end);
        sort(array, start, middle - 1);
        sort(array, middle + 1, end);
    }

    public int part(int[] array, int start, int end) {
        int value = array[start];
        while (start < end){
            while (start < end && array[end] >= value){
                end--;
            }
            if (start < end) {
                array[start] = array[end];
                start++;
            }
            while (start < end && array[start] <= value){
                start++;
            }
            if (start < end) {
                array[end] = array[start];
                end--;
            }
        }
        array[start] = value;
        return start;
    }
}
