package com.data.algorithm.practice.other;

/**
 * RemoveDuplicateElements:
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * @author sunchen
 * @date 2020/7/22 3:04 下午
 */
public class RemoveDuplicateElement {
    /**
     * 1，2，2，1，3，4，1，1，1
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 2, 1, 3};
        int length = handler(array);
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }

    public static int handler(int[] array) {
        int length = array.length;
        int i = 0;
        while (i < length) {
            boolean isChange = false;
            int j = i + 1;
            while (j < length) {
                if (array[i] == array[j]) {
                    isChange = true;
                    if (j < length - 1) {
                        int temp = array[length - 1];
                        array[length - 1] = array[j];
                        array[j] = temp;
                    }
                    length--;
                } else {
                    j++;
                }
            }
            if (isChange) {
                if (i < length - 1) {
                    int temp = array[length - 1];
                    array[length - 1] = array[i];
                    array[i] = temp;
                }
                length--;
            } else {
                i++;
            }
        }
        return length;
    }
}
