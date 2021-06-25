package com.data.basics.design.factory.simplefactory;

/**
 * SimpleVehicleFactory:
 * 简单，但是可扩展性差
 *
 * @author sunchen
 * @date 2020/7/9 2:25 下午
 */
public class SimpleVehicleFactory {
    public static Car createCar() {
        return new Car();
    }

    public static Broom createBroom() {
        return new Broom();
    }
}
