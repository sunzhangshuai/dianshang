package com.data.basics.design.decorator;

/**
 * MilkDecorator:
 *
 * @author sunchen
 * @date 2020/7/15 4:47 下午
 */
public class MilkDecorator extends AbstractCoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + "+ Milk";
    }
}
