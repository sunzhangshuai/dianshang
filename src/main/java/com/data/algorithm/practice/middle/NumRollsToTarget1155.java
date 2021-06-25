package com.data.algorithm.practice.middle;

import java.util.HashMap;

/**
 * NumRollsToTarget1155:
 * 这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
 * 我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
 * 如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回)
 *
 * @author sunchen
 * @date 2020/7/27 6:18 下午
 */
public class NumRollsToTarget1155 {
    public static void main(String[] args) {
        handler(3, 10, 50);
    }

    static HashMap<String, Double> cache = new HashMap<>();

    public static int handler(int d, int f, int target) {
        if (d * f < target || d <= 0 || target <= 0) {
            return 0;
        }
        if (d * f == target || d == 1 || d == target) {
            return 1;
        }
        String key = d + "-" + target;
        if (!cache.containsKey(key)) {
            double sum = 0;
            for (int i = 1; i <= f; i++) {
                sum += handler(d - 1, f, target - i);
            }
            sum = (sum % (Math.pow(10, 9) + 7));
            cache.put(key, sum);
        }
        Double aDouble = cache.get(key);
        return aDouble.intValue();
    }
}
