package com.data.algorithm.practice.middle;

import java.util.HashMap;

/**
 * Convert6:
 * z子形变形
 *
 * @author sunchen
 * @date 2020/7/15 3:23 下午
 */
public class Convert6 {
    public String convert(String s, int numRows) {
        boolean sub = false;
        int index = 0;
        HashMap<Integer,StringBuilder> maps = new HashMap<Integer,StringBuilder>();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (index == 0) {
                sub = false;
            }
            if (index == numRows-1) {
                sub = true;
            }
            StringBuilder sb = maps.get(index);
            if (sb == null) {
                sb = new StringBuilder();
                sb.append(c[i]);
            } else {
                sb.append(c[i]);
            }
            if (sub){
                index--;
            }
            if (!sub){
                index++;
            }
        }
        StringBuilder newSb = new StringBuilder();
        for (Integer i : maps.keySet()) {
            StringBuilder sb = maps.get(i);
            newSb.append(sb);
        }
        return newSb.toString();
    }
}
