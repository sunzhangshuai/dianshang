package com.data.algorithm.practice.primary;

/**
 * AddDigitsTest:
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * @author sunchen
 * @date 2020/7/23 6:12 下午
 */
public class AddDigits256 {

    public static void main(String[] args) {
        int result = handel(9399);
        System.out.println(result);
        int result1 = addDigits(9399);
        System.out.println(result1);
    }

    public static int handel(int num) {
        int sum = 0;
        while (num >= 10) {
            sum += num % 10;
            num = num / 10;
            if (num < 10) {
                num = num + sum;
                sum = 0;
            }
        }
        return num;
    }

    public static int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
