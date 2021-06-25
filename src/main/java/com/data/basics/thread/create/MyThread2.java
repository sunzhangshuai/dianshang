package com.data.basics.thread.create;

/**
 * MyThread2:
 *
 * @author sunchen
 * @date 2020/6/4 2:53 下午
 */
public class MyThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + "" + i);
        }
    }
}
