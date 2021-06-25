package com.data.basics.design.proxy.staticproxy;

import java.util.Random;

/**
 * Tank:
 *
 * @author sunchen
 * @date 2020/7/17 12:52 上午
 */
public class Tank implements MoveAble{
    @Override
    public void move() {
        System.out.println("移动。。。。。。。。。。。");
        try {
            Thread.sleep((long) (Math.random()*10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
