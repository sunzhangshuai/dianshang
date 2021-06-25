package com.data.basics.design.factory.factorymethod;

/**
 * CarFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:29 下午
 */
public class CarFactory implements Factory{
    @Override
    public Car create(){
        System.out.println("car ......");
       return new Car();
    }
}
