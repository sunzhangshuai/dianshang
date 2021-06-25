package com.data.basics.design.singlton;

/**
 * Singleton1:
 * 多线程下没有问题 效率降低
 *
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public class Singleton3 {
    private Singleton3() {
    }

    private static Singleton3 singleton2;

    public synchronized static Singleton3 getInstance() {
        if (singleton2 == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton2 = new Singleton3();
        }
        return singleton2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton3.getInstance().hashCode());
                }
            }).start();
        }
    }
}
