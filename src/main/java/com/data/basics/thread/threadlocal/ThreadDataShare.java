package com.data.basics.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ThreadDataShare:
 * 线程内数据共享
 *
 * @author sunchen
 * @date 2020/6/30 9:49 上午
 */
public class ThreadDataShare {
    private static int data = 0;
    private static final Map<Thread, Integer> datas = new HashMap<Thread, Integer>();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = new Random().nextInt();
                    datas.put(Thread.currentThread(), data);
                    System.out.println(Thread.currentThread() + "put data :" + data);
                    new Model1().get();
                    new Model2().get();
                }
            }).start();
        }
    }
    static class Model1 {
        public void get() {
            int data = datas.get(Thread.currentThread());
            System.out.println("model1 from " + Thread.currentThread() + "get data :" + data);
        }
    }

    static class Model2 {
        public void get() {
            int data = datas.get(Thread.currentThread());
            System.out.println("model2 from " + Thread.currentThread() + "get data :" + data);
        }
    }


}
