package com.data.algorithm.practice.primary;

/**
 * IsHappy:
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False
 * @author sunchen
 * @date 2020/7/22 7:07 下午
 */
public class IsHappy202 {
    public static void main(String[] args) {
       boolean result =  handler(19);
        System.out.println(result);
    }

    public static boolean handler(int n) {
        if (n == 1) {
            return true;
        }
        int slow = getNext(n);
        int fast = getNext(getNext(n));
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;

    }

    public static int getNext(int num) {
        int sum = 0;
        while (num > 0) {
            int mod = Math.floorMod(num, 10);
            sum += mod * mod;
            num = Math.floorDiv(num, 10);
        }
        return sum;
    }
}
