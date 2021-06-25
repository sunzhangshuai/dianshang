package com.data.basics.design.factory.abstractfactory;

/**
 * AbstractFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:46 下午
 */
public abstract class AbstractFactory {
    /**
     * 生产吃的
     * @return
     */
    abstract Food createFood();

    /**
     * 生产工具的
     * @return
     */
    abstract Vehicle createVehicle();
}
