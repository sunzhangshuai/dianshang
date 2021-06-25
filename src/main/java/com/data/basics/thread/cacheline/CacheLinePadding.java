package com.data.basics.thread.cacheline;

/**
 * CacheLinePadding:
 * 缓存行对齐
 *
 * @author sunchen
 * @date 2020/7/6 5:54 下午
 */
public class CacheLinePadding {
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;//8个字节
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 10000000L; i++) {
                    arr[0].x = i;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 10000000L; i++) {
                    arr[1].x = i;
                }
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
    }
}
