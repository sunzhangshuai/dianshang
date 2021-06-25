package com.data.basics.thread.sync;

/**
 * ThreadCreator:
 *
 * @author sunchen
 * @date 2020/6/29 10:22 上午
 */
public class ThreadCreator {
    public static void main(String[] args) {
        Milk m = new Milk();
        Producer producer = new Producer(m);
        Customer customer = new Customer(m);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(customer);
        thread1.start();
        thread2.start();
    }
}
