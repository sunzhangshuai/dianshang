package com.data.basics.design.factory.factorymethod;

/**
 * CarFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:29 下午
 */
public class BoomFactory implements Factory{
    @Override
    public  Broom create(){
       return new Broom();
    }
}
