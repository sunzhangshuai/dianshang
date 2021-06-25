package com.data.basics.design.decorator;

/**
 * CoffeeDecorator:
 *
 * @author sunchen
 * @date 2020/7/15 4:45 下午
 */
public abstract class AbstractCoffeeDecorator implements Coffee {
    Coffee coffee;

    public AbstractCoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public String getIngredients() {
        return coffee.getIngredients();
    }
}
