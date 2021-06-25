package com.data.structure.sort;

public abstract class Sort {

    public void sort (int[] array){}

    public void sort (int[] array, int start, int end){}

    public int[] getMaxMin (int[] array) {
        int[] maxMin = new int[2];
        int max = array[0], min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        maxMin[0] = max;
        maxMin[1] = min;
        return maxMin;
    }
}
