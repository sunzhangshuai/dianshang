package com.data.basics.design.proxy.dynicproxy.jdk;

import com.data.basics.design.proxy.staticproxy.MoveAble;

import java.lang.reflect.Proxy;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/17 2:03 上午
 */
public class Test {
    public static void main(String[] args) {
        Tank tank = new Tank();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        MoveAble m  = (MoveAble) Proxy.newProxyInstance(Tank.class.getClassLoader(), new Class[]{MoveAble.class}, new LogProxy(tank));
        m.move();
    }
}
