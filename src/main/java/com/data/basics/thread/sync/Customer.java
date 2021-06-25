package com.data.basics.thread.sync;

/**
 * Customer:
 * 生产者
 *
 * @author sunchen
 * @date 2020/6/28 6:27 下午
 */
public class Customer implements Runnable{

    public Milk milk;

    public Customer(Milk milk){
        this.milk = milk;
    }

    @Override
    public void run() {
        try {
            while (true) {
                milk.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
