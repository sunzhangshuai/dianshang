package com.data.basics.thread.threadlocal;

import sun.security.krb5.internal.Ticket;

/**
 * MutilDataShare:
 * 多个线程共享一个数据
 *
 * @author sunchen
 * @date 2020/7/1 10:14 上午
 */
public class MultiDataShareSellTicket {
    /**
     * 1. 5个线程一起卖票
     */
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        for (int i = 0; i < 5; i++) {
            new Thread(tickets).start();
        }
    }


}

class Tickets implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖第" + ticket + "张票");
                    ticket--;
                }
            }
        }
    }
}
