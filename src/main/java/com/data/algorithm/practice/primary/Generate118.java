package com.data.algorithm.practice.primary;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate118:
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * @author sunchen
 * @date 2020/7/26 10:45 下午
 */
public class Generate118 {
    static List<List<Integer>> lists = new ArrayList<>();

    public static void main(String[] args) {
        List<List<Integer>> generate = generate2(5);
        System.out.println(generate);
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        result.add(row1);
        for (int row = 1; row < numRows; row++) {
            List<Integer> rowRes = new ArrayList<>();
            rowRes.add(1);
            for (int i = 1; i < row; i++) {
                int res = result.get(row - 1).get(i - 1) + result.get(row - 1).get(i);
                rowRes.add(res);
            }
            rowRes.add(1);
            result.add(rowRes);
        }
        return result;
    }

    public static List<List<Integer>> generate2(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        if (numRows == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            lists.add(list);
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        generate2(numRows - 1);
        for (int i = 1; i < numRows - 1; i++) {
            int res = lists.get(numRows - 2).get(i - 1) + lists.get(numRows - 2).get(i);
            list.add(res);
        }
        list.add(1);
        lists.add(list);
        return lists;
    }
}
