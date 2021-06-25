package com.data.structure.sort;

/**
 * 归并排序
 *
 * @author sunchen
 */
public class MergeSort extends Sort {
    @Override
    public void sort(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = (start + end) / 2;
        sort(array, start, middle);
        sort(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    public void merge(int[] array, int start, int middle, int end) {
        int[] newArray = new int[end - start + 1];
        int index1 = start;
        int index2 = middle + 1;
        int index = 0;
        while (index1 <= middle && index2 <= end) {
            if (array[index1] <= array[index2]) {
                newArray[index++] = array[index1];
                index1++;
            } else {
                newArray[index++] = array[index2];
                index2++;
            }
        }
        while (index1 <= middle) {
            newArray[index++] = array[index1++];
        }

        while (index2 <= end) {
            newArray[index++] = array[index2++];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[start + i] = newArray[i];
        }
    }

}
