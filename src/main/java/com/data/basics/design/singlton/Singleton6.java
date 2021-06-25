package com.data.basics.design.singlton;

/**
 * Singleton1:
 * 防止反序列化
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public enum  Singleton6 {
    /**
     * 单例
     */
    INSTANSE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton6.INSTANSE.hashCode());
                }
            }).start();
        }
    }
}
