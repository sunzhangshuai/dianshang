package com.data.basics.design.factory.simplefactory;

/**
 * Main:
 *
 * @author sunchen
 * @date 2020/7/9 2:24 下午
 */
public class Main {
    public static void main(String[] args) {
//        Car car = new Car();
//        car.go();
//        Broom broom = new Broom();
//        broom.go();
        MoveAble move = SimpleVehicleFactory.createCar();
    }
}
