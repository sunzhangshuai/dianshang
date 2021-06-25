package com.data.basics.design.factory.abstractfactory;

/**
 * MagicFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:51 下午
 */
public class MagicFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new Shugu();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }
}
