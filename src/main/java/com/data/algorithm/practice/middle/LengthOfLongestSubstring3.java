package com.data.algorithm.practice.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * SubStringTest:
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author sunchen
 * @date 2020/7/8 2:56 下午
 */
public class LengthOfLongestSubstring3 {

    public static int lengthOfLongestSubstring2(String s) {
        int[] array = new int[s.length()];
        int arrayIndex = 0;
        int max = 0;
        List<Character> list = new ArrayList<Character>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!list.contains(chars[i])) {
                list.add(i, chars[i]);
                array[arrayIndex] = list.size();
            } else {
                int index = list.indexOf(chars[i]);
                array[arrayIndex] = i - index;
                list.add(i, chars[i]);
            }
            if (max < array[arrayIndex]) {
                max = array[arrayIndex];
            }
            arrayIndex++;
        }
        return max;
    }

    public static void main(String[] args) {
        int result = lengthOfLongestSubstring2("pwwkew");
        System.out.println(result);
    }
}
