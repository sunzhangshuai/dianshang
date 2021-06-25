package com.data.algorithm.practice.difficulty;

/**
 * FindMedianSortedArrays4:
 *
 * @author sunchen
 * @date 2020/7/8 8:08 下午
 */
public class FindMedianSortedArrays4 {
    public static int findMedianSortedArrays(int[] a, int[] b) {
        int middle = (a.length + b.length) % 2 == 0 ? (a.length + b.length) / 2 : (a.length + b.length) / 2 + 1;
        int result1 = 0;
        int result2 = 0;
        int count = 0;
        int i, j = 0;
        int flag = 0;
        for (i = 0, j = 0; count <= middle - 1;) {
            if (a[i] < b[j]) {
                result1 = b[j];
                i++;
                flag = 2;
            } else {
                result1 = a[i];
                j++;
                flag = 1;
            }
            count += 2;
        }
        if ((a.length + b.length) % 2 == 0) {
            if (flag == 1) {
                result2 = Math.min(a[Math.min(i+1, a.length-1)], b[j]);
            } else {
                result2 = Math.min(a[i], b[Math.min(j+1, b.length-1)]);
            }
            return (result1 + result2)/2;
        }
        return result1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3};
        int[] b = {2, 4, 5};
        int result = findMedianSortedArrays(a, b);
        System.out.println(result);
    }
}
