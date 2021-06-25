package com.data.basics.design.singlton;

/**
 * Singleton1:
 * 多线程下没有问题
 * 减少锁的范围
 * 双重判断
 * jvm类只加载一次 Singleton5和SingletonHolder都只加载一次 singleton5也加载一次
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public class Singleton5 {
    private Singleton5() {
    }

    private static class SingletonHolder{
        private static final Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton5.getInstance().hashCode());
                }
            }).start();
        }
    }
}
