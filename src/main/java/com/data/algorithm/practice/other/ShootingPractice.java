package com.data.algorithm.practice.other;

import java.util.HashMap;

/**
 * ShootingPractice:
 * 打靶次数
 * 10枪内打中90环的概率
 *
 * @author sunchen
 * @date 2020/7/27 12:56 下午
 */
public class ShootingPractice {
    public static void main(String[] args) {
        int num = handler(10, 90);
        System.out.println(num);
    }
    static HashMap<String, Integer> cache = new HashMap<>();
    /**
     * @param times   次数
     * @param ringNum 环数
     */
    public static int handler(int times, int ringNum) {
        if (times * 10 < ringNum || times < 0 || ringNum < 0) {
            return 0;
        }
        if (times * 10 == ringNum || ringNum == 0) {
            return 1;
        }
        int sum = 0;
        String key = times + "-" + ringNum;
        if (!cache.containsKey(key)){
            for (int i = 0; i <= 10; i++) {
                sum += handler(times - 1, ringNum - i);
            }
            cache.put(key, sum);
        }

        return cache.get(key);
    }
}
