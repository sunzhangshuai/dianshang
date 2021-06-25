package com.data.jvm;

/**
 * JvmStatcksTest:
 *
 * @author sunchen
 * @date 2020/7/9 5:27 下午
 */
public class JvmStacksTest3 {
    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append("w");
        sb.append("o");
        new Thread(new Runnable() {
            @Override
            public void run() {
                method1(sb);
            }
        }).start();
    }

    public static void method1(StringBuilder sb){
        sb.append("1");
        sb.append("2");
        sb.append("3");
        System.out.println(sb);
    }
}
