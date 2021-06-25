package com.data.structure.sort;

/**
 * 直接插入排序
 */
public class InsertSort extends Sort{

    public void sort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (temp < array[j-1]){
                    array[j] = array[j-1];
                }else{
                    break;
                }
            }
            array[j] = temp;
        }
    }
}
