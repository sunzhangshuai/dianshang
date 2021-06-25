package com.data.basics.thread.volatilepack;

import java.util.concurrent.TimeUnit;

/**
 * VolatileTest:
 * volatile 实现线程内可见
 * @author sunchen
 * @date 2020/7/6 4:44 下午
 */
public class VolatileTest {
    volatile boolean running = true;
    void m(){
        System.out.println("m start");
        while (running){};
        System.out.println("m end");
    }
    public static void main(String[] args) {
        final VolatileTest vv = new VolatileTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                vv.m();
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vv.running = false;
    }
}
