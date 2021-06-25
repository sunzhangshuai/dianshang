package com.data.jvm;

/**
 * JvmStatcksTest:
 *
 * @author sunchen
 * @date 2020/7/9 5:27 下午
 */
public class JvmStacksTest2 {
    static int x = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int j = 0; j < 5000; j++) {
                       x++;
                    }
                    System.out.println(x+Thread.currentThread().getName());
                }
            }).start();
        }
    }

    public static int method1() {
        return method2(4, 5);
    }

    public static int method2(int a, int b) {
        int c = a + b;
        return c;
    }
}
