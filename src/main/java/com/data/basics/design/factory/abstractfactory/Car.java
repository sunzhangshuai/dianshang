package com.data.basics.design.factory.abstractfactory;

import com.data.basics.design.factory.factorymethod.MoveAble;

/**
 * Car:
 *
 * @author sunchen
 * @date 2020/7/9 2:23 下午
 */
public class Car extends Vehicle {
    @Override
    public void go(){
        System.out.println("car");
    }
}
