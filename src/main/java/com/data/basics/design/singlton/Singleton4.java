package com.data.basics.design.singlton;

/**
 * Singleton1:
 * 多线程下没有问题
 * 减少锁的范围
 * 双重判断
 *
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public class Singleton4 {
    private Singleton4() {
    }

    private volatile static Singleton4 singleton2;

    public static Singleton4 getInstance() {
        if (singleton2 == null) {
            //业务代码
            synchronized (Singleton4.class) {
                if (singleton2 == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    singleton2 = new Singleton4();
                }
            }
        }

        return singleton2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton4.getInstance().hashCode());
                }
            }).start();
        }
    }
}
