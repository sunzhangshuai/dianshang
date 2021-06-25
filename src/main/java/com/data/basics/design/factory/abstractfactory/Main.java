package com.data.basics.design.factory.abstractfactory;

import com.data.basics.design.factory.factorymethod.Car;
import com.data.basics.design.factory.factorymethod.CarFactory;
import com.data.basics.design.factory.factorymethod.Train;
import com.data.basics.design.factory.factorymethod.TrainFactory;

/**
 * Main:
 *
 * @author sunchen
 * @date 2020/7/9 2:24 下午
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory magicFactory = new MagicFactory();
        Food food = magicFactory.createFood();
        food.eat();
        Vehicle vehicle = magicFactory.createVehicle();
        vehicle.go();
    }
}
