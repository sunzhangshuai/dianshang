package com.data.basics.thread.create;

/**
 * MyThread:
 *
 * @author sunchen
 * @date 2020/6/4 2:44 下午
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100;i++) {
            System.out.println(Thread.currentThread()+""+i);
        }
    }
}
