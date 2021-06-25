package com.data.algorithm.practice.middle;

import java.util.HashMap;

/**
 * MyPow50:
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * @author sunchen
 * @date 2020/7/27 12:27 上午
 */
public class MyPow50 {
    public static void main(String[] args) {
        double result = handler2(1.00000
                , -2147483648);
        System.out.println(result);
    }

    public static double handler(double x, int n) {
        int i = 0;
        double sum = 1;
        while (i < Math.abs(n)) {
            sum *= x;
            i++;
        }
        if (n < 0) {
            return (double) 1 / sum;
        }
        return sum;
    }
    static HashMap<Integer, Double> cache = new HashMap<>();
    public static double handler2(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }

        if (!cache.containsKey(n)) {
            cache.put(n, handler2(x, n / 2) * handler2(x, n - n / 2));
        }
        return cache.get(n);
    }
}