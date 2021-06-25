package com.data.algorithm.practice.primary;

import java.util.HashMap;

/**
 * ClimbStairs70:
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author sunchen
 * @date 2020/7/26 10:13 下午
 */
public class ClimbStairs70 {

    public static void main(String[] args) {
        int result = handler(3);
        System.out.println(result);
    }
    static HashMap<Integer, Integer> cache = new HashMap<>();
    public static int handler(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if(!cache.containsKey(n)){
            int result = handler(n - 1) + handler(n - 2);
            cache.put(n, result);
        }
        return cache.get(n);
    }
}
