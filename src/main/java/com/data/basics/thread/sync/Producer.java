package com.data.basics.thread.sync;

/**
 * Producer:
 * 消费者
 *
 * @author sunchen
 * @date 2020/6/28 6:27 下午
 */
public class Producer implements Runnable {
    public Milk milk;

    public Producer(Milk milk) {
        this.milk = milk;
    }

    @Override
    public void run() {
        try {

            for (int i = 1; i <= 30; i++) {
                milk.put(i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
