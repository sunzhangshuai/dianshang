package com.data.basics.design.proxy.dynicproxy.cglib;


import net.sf.cglib.proxy.Enhancer;

/**
 * CglibTest:
 *
 * @author sunchen
 * @date 2020/7/17 3:00 上午
 */
public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CgTank.class);
        enhancer.setCallback(new TimeInterceptor());
        CgTank tank = (CgTank) enhancer.create();
        tank.move();
    }
}
