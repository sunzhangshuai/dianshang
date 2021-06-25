package com.data.algorithm.practice.middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WordBreak139:
 *
 * @author sunchen
 * @date 2020/8/31 11:43 下午
 */
public class WordBreak139 {
    public static boolean handler(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args,int ... length) {
        ArrayList list = new ArrayList<>();
        list.add("le");
        list.add("leet");
        handler("leetleet", list);
    }
}
