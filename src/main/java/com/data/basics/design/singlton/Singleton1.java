package com.data.basics.design.singlton;

/**
 * Singleton1:
 *
 * @author sunchen
 * @date 2020/7/7 5:19 下午
 */
public class Singleton1 {
    private Singleton1(){}
    private static final Singleton1 SINGLETON1 = new Singleton1();
    public static Singleton1 getInstance(){
        return SINGLETON1;
    }

}
