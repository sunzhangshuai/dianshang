package com.data.basics.design.factory.simplefactory;

/**
 * Car:
 *
 * @author sunchen
 * @date 2020/7/9 2:23 下午
 */
public class Car implements MoveAble{
    @Override
    public void go(){
        System.out.println("car");
    }
}
