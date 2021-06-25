package com.data.basics.design.proxy.staticproxy;

/**
 * TimeProxy:
 *
 * @author sunchen
 * @date 2020/7/17 12:54 上午
 */
public class LogProxy implements MoveAble{
    MoveAble moveAble;
    public LogProxy(MoveAble moveAble){
        this.moveAble = moveAble;
    }

    @Override
    public void move() {
        System.out.println("start--------");
        moveAble.move();
        System.out.println("end-----------");
    }
}
