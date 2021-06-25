package com.data.algorithm.practice.middle;

import java.util.HashSet;

/**
 * Palindrome5:
 *
 * @author sunchen
 * @date 2020/7/8 7:03 下午
 */
public class Palindrome5 {

    private int index, len;

    /**
     * 以每个数为中心，偶数长度的回文和奇数长度的回文的最大值
     * @param s
     * @return
     */
    public  String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s.substring(0, 1);
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            palindromeHelper(i, i, s);
            palindromeHelper(i, i+1, s);
        }
        return s.substring(index, index + len);
    }

    public  void palindromeHelper(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (len < right - left - 1) {
            index = left + 1;
            len = right - left - 1;
        }
    }
}
