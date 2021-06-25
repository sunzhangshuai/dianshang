package com.data.basics.design.proxy.staticproxy;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/17 12:57 上午
 */
public class Test {
    public static void main(String[] args) {
        LogProxy logProxy = new LogProxy(new TimeProxy(new Tank()));
        logProxy.move();
    }
}
