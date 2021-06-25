package com.data.structure.sort;

/**
 * 希尔排序
 */
public class ShellSort extends Sort{
    //{1,4,5,2,6,3,9}
    // 1 4  3  2  6  5 9 7
    @Override
    public void sort(int[] array) {
        //分组排序，分好的组用直接插入排序
        for (int group = array.length/2; group >=1; group=group/2) {
            //取出需要排序的数
            for (int j = group; j < array.length; j++) {
                int temp = array[j];
                int k;
                for (k = j-group; k>=0; k-=group) {
                    if (temp < array[k]) {
                        array[k+group] = array[k];
                    } else {
                        break;
                    }
                }
                array[k+group] = temp;
            }
        }
    }
}
