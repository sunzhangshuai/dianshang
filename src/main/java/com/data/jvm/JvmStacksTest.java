package com.data.jvm;

/**
 * JvmStatcksTest:
 *
 * @author sunchen
 * @date 2020/7/9 5:27 下午
 */
public class JvmStacksTest {
    public static void main(String[] args) {
        int result = method1();
        System.out.println(result);
    }

    public static int method1() {
        return method2(4, 5);
    }

    public static int method2(int a, int b) {
        int c = a + b;
        return c;
    }
}
