package com.data.jvm;

/**
 * HeapTest:
 *
 * @author sunchen
 * @date 2020/7/10 7:42 下午
 */
public class HeapTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1---------");
        Thread.sleep(30000);
        byte[] bytes = new byte[1024*1024*10];
        System.out.println("2----------");
        Thread.sleep(30000);
        bytes = null;
        System.gc();
        System.out.println("3-------");
        Thread.sleep(10000000L);
    }
}
