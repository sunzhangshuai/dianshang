package com.data.basics.design.decorator;

/**
 * SugarDecorator:
 *
 * @author sunchen
 * @date 2020/7/15 4:50 下午
 */
public class SugarDecorator extends AbstractCoffeeDecorator{
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + "+ sugar";
    }
}
