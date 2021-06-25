package com.data.basics.design.singlton;

/**
 * Singleton1:
 * 多线程情况下会有问题
 *
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton2;

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton2 = new Singleton2();
        }
        return singleton2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton2.getInstance().hashCode());
                }
            }).start();
        }
    }
}
