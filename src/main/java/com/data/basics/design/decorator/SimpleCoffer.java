package com.data.basics.design.decorator;

/**
 * SimpleCoffer:
 *
 * @author sunchen
 * @date 2020/7/15 4:43 下午
 */
public class SimpleCoffer implements Coffee{

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}
