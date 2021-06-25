package com.data.basics.design.factory.abstractfactory;

import com.data.structure.tree.impl.tree.AbstractTree;

/**
 * ModernFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:46 下午
 */
public class ModernFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}
