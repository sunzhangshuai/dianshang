package com.data.basics.thread.create;

import com.data.basics.thread.create.MyThread;
import com.data.basics.thread.create.MyThread2;

/**
 * ThreadTest:
 *
 * @author sunchen
 * @date 2020/6/4 2:45 下午
 */
public class ThreadTest {
    public static void main(String[] args) {
        //创建线程的第一种方式
        MyThread thread1 = new MyThread();
        thread1.setName("sunchen");
        thread1.start();
        MyThread thread2 = new MyThread();
        thread2.setName("zhangshuai");
        thread2.start();
        //创建线程的第二种方式
        MyThread2 thread = new MyThread2();
        new Thread(thread2, "laosun").start();
    }
}
