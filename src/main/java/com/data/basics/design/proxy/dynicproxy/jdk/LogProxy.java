package com.data.basics.design.proxy.dynicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * LogProxy:
 *
 * @author sunchen
 * @date 2020/7/17 2:04 上午
 */
public class LogProxy implements InvocationHandler {
    Tank tank;
    public LogProxy(Tank tank){
        this.tank = tank;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start.........");
        Object invoke = method.invoke(tank, null);
        System.out.println("end...........");
        return invoke;
    }
}
