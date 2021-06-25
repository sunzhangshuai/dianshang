package com.data.basics.thread.sync;

/**
 * Milk:
 *
 * @author sunchen
 * @date 2020/6/28 6:29 下午
 */
public class Milk {
    private int i;
    /**
     * 标识是否有牛奶可以消费
     */
    private boolean status = false;

    /**
     * 生产牛奶
     */
    public synchronized void put (int i) throws InterruptedException {
        if (status) {
            wait();
        }
        this.i = i;
        System.out.println("生产了第" + this.i + "瓶牛奶");
        status = true;
        notifyAll();
    }

    /**
     * 消费牛奶
     */
    public synchronized void get () throws InterruptedException {
        if (!status) {
            wait();
        }
        System.out.println("消费了第" + this.i + "瓶牛奶");
        status = false;
        notifyAll();
    }
}
