package com.data.basics.design.proxy.dynicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TimeInteceptor:
 *
 * @author sunchen
 * @date 2020/7/17 3:03 上午
 */
public class TimeInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start..........");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("end.............");
        return o1;
    }
}
