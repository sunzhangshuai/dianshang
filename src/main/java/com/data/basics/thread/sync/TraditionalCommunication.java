package com.data.basics.thread.sync;

/**
 * TraditionalCommunication:
 * 传统的通信
 *
 * @author sunchen
 * @date 2020/6/29 1:55 下午
 */
public class TraditionalCommunication {

    /**
     * 子线程执行3次，主线程执行5次，如此循环10次
     * @param args
     */
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub(i);
                }
            }
        }).start();
        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }


    static class Business {
        public boolean status = true;

        public synchronized void sub(int i) {
            if (!status) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 3; j++) {
                System.out.println("sub sequence " + j + "loop of" + i);
            }
            status = false;
            this.notifyAll();
        }

        public synchronized void main(int i) {
            if (status) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 5; j++) {
                System.out.println("main sequence " + j + "loop of" + i);
            }
            status = true;
            this.notifyAll();
        }

    }
}
