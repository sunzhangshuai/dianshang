package com.data.algorithm.searchtable;

import com.data.structure.searchtable.dynamicsearchtable.impl.BinarySortTreeSearchTableImpl;

/**
 * BinarySortTree:
 *
 * @author sunchen
 * @date 2020/6/28 5:14 下午
 */
public class BinarySortTree extends BinarySortTreeSearchTableImpl {


    /**
     * 二叉排序树的平均查找长度
     *
     * @return
     */
    public int search(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (1 + search(i) * i + search(n - i - 1) * (n - i - 1));
        }
        return sum/n;
    }
    public static void getSum(String str1, String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 标识是否有进位
        boolean flag = false;
        int i = 0;
        while (i < chars1.length && i < chars2.length){
            int sum = chars1[i] + chars2[i];
            if (flag) {
                sum += 1;
            }
            if (sum - 10 > 0) {
                flag = true;
                sb.append(sum - 10);
            } else {
                flag = false;
                sb.append(sum);
            }
            i++;
        }
        while (i < chars1.length) {
            int sum = chars1[i];
            if (flag) {
                sum += 1;
            }
            if (sum - 10 > 0) {
                flag = true;
                sb.append(sum - 10);
            } else {
                flag = false;
                sb.append(sum);
            }
            i++;
        }

        while (i < chars2.length) {
            int sum = chars2[i];
            if (flag) {
                sum += 1;
            }
            if (sum - 10 > 0) {
                flag = true;
                sb.append(sum - 10);
            } else {
                flag = false;
                sb.append(sum);
            }
            i++;
        }
    }

    public static void main(String[] args) {

    }
}
