package com.data.basics.design.factory.factorymethod;

/**
 * Main:
 *
 * @author sunchen
 * @date 2020/7/9 2:24 下午
 */
public class Main {
    public static void main(String[] args) {
        Factory f = new CarFactory();
        f.create();
    }
}
