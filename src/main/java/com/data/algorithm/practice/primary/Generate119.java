package com.data.algorithm.practice.primary;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate119:
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * @author sunchen
 * @date 2020/7/26 10:45 下午
 */
public class Generate119 {

    public static void main(String[] args) {
        List<Integer> generate = generate(13);
        System.out.println(generate);
    }

    public static List<Integer> generate(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if (rowIndex == 0) {
            return result;
        }
        int res = 1;
        for (int i = 1; i < rowIndex; i++) {
            res = res * (rowIndex - i + 1) / i;
            result.add(res);
        }
        result.add(1);
        return result;
    }
}
