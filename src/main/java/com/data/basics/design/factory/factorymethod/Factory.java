package com.data.basics.design.factory.factorymethod;

/**
 * Factory:
 *
 * @author sunchen
 * @date 2020/7/9 2:58 下午
 */
public interface Factory {
    /**
     * 生产
     * @return
     */
    MoveAble create();
}
