package com.data.basics.design.decorator;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/15 4:51 下午
 */
public class Test {
    public static void main(String[] args) {
        AbstractCoffeeDecorator decorator = new MilkDecorator(new SimpleCoffer());
        System.out.println("价格: "+ decorator.getCost() + "元");
        System.out.println("材料: "+decorator.getIngredients());

        AbstractCoffeeDecorator decorator1 = new SugarDecorator(new MilkDecorator(new SimpleCoffer()));
        System.out.println("价格: " + decorator1.getCost() + "元");
        System.out.println("材料: " + decorator1.getIngredients());
    }
}
