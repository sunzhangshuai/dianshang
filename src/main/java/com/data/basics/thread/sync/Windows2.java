package com.data.basics.thread.sync;

/**
 * Windows2:
 * synchronized用法：
 * 用在并发的时候，同步线程
 * @author sunchen
 * @date 2020/6/4 3:00 下午
 */
public class Windows2 implements Runnable {
    int ticket = 10;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    /**
     * synchronized 修饰非静态变量
     */
    public synchronized void sale() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread() + "卖" + ticket + "号票");
                ticket--;
            }

    }

    /**
     * 下面的写法与上面的等价
     */
    public void sale1() {
        synchronized (this){
            if (ticket > 0) {
                System.out.println(Thread.currentThread() + "卖" + ticket + "号票");
                ticket--;
            }
        }
    }

    /**
     * synchronized 修饰静态变量
     */
    public static synchronized void sale3(){
        System.out.println("just test");
    }

    /**
     * 下面的方式与上面的等价
     */
    public static void sale4() {
        synchronized (Windows2.class){
            System.out.println("just test");
        }
    }

    public static void main(String[] args) {
        Windows2 w = new Windows2();
        new Thread(w, "窗口一").start();
        new Thread(w, "窗口二").start();
        new Thread(w, "窗口三").start();
        new Thread(w, "窗口四").start();
    }
}
