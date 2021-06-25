package com.data.basics.thread.sync;

import com.data.basics.thread.create.MyThread;

/**
 * Windows:
 *
 * @author sunchen
 * @date 2020/6/4 2:59 下午
 */
public class Windows extends Thread{
    static int ticket;
    @Override
    public void run() {
        while (true){
            if (ticket > 0) {
                System.out.println(Thread.currentThread()+"卖"+ticket+"号票");
                ticket--;
            }
        }

    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();
        MyThread thread4 = new MyThread();
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread4.setName("窗口四");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
