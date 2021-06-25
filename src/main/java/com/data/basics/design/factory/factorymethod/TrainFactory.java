package com.data.basics.design.factory.factorymethod;

/**
 * TrainFactory:
 *
 * @author sunchen
 * @date 2020/7/9 2:30 下午
 */
public class TrainFactory implements Factory{
    @Override
    public  Train create(){
        return new Train();
    }
}
