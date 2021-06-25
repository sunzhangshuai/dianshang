package com.data.basics.design.proxy.staticproxy;

/**
 * TimeProxy:
 *
 * @author sunchen
 * @date 2020/7/17 12:54 上午
 */
public class TimeProxy implements MoveAble{
    MoveAble moveAble;
    public TimeProxy(MoveAble moveAble){
        this.moveAble = moveAble;
    }
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        moveAble.move();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
