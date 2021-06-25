package com.data.algorithm.practice.primary;

/**
 * Reverse7:
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * @author sunchen
 * @date 2020/7/26 3:32 下午
 */
public class Reverse7 {
    static int base = 1;

    public static void main(String[] args) {
        int result = handler(-120);
        System.out.println(result);
    }
    public static int handler(int num) {
        int signal = 1;
        if (num < 0) {
            signal = -1;
        }
        long result = getReverse(Math.abs(num));
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) (signal * result);
        }
    }

    public static long getReverse(int num) {
        if (num < 10) {
            return num;
        }
        int mod = num % 10;
        num = num / 10;
        long result = getReverse(num);
        base *= 10;
        return mod * base + result;
    }

    public static int handler2(int num) {
        int rev = 0;
        while (num != 0) {
            int pop = num % 10;
            num = num / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
