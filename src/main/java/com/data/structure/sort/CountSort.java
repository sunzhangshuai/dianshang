package com.data.structure.sort;

/**
 * 计数排序
 */
public class CountSort extends Sort{

    @Override
    public void sort(int[] array) {
        int[] maxMin = getMaxMin(array);
        int max = maxMin[0], min = maxMin[1];
        int countLength = max - min + 1;
        int[] countArray = new int[countLength];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;
        }
        int preTemp = countArray[0];
        countArray[0] = 0;
        for (int i = 1; i < countArray.length; i++) {
            int temp = countArray[i];
            countArray[i] = countArray[i-1] + preTemp;
            preTemp = temp;
        }

        int [] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[countArray[array[i]-min]] = array[i];
            countArray[array[i]-min]++;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
