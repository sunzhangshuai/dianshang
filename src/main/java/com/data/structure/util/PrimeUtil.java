package com.data.structure.util;

/**
 * PrimUtil:
 *
 * @author sunchen
 * @date 2020/7/12 8:32 下午
 */
public class PrimeUtil {
    public static int prime(int data) {
        int start = 2;
        for (int i = data; i > 0; i--) {
            boolean status = true;
            for (int j = start; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    status = false;
                    break;
                }
            }
            if (status) {
                return i;
            }
        }
        throw new RuntimeException("没有找到不大于" + data + "的素数");
    }
}
