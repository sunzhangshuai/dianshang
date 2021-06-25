package com.data.basics.thread.threadlocal;

import java.util.Random;

/**
 * ThreadLocalTest:
 *
 * @author sunchen
 * @date 2020/6/30 4:18 下午
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    ThreadLocalDatas datas = ThreadLocalDatas.getThreadInstance();
                    datas.setAge(data);
                    datas.setName("name:" + data);
                    System.out.println(Thread.currentThread() + "put data :" + datas);
                    new ThreadLocalTest.Model1().get();
                    new ThreadLocalTest.Model2().get();
                }
            }).start();
        }
    }

    static class Model1 {
        public void get() {
            ThreadLocalDatas datas = ThreadLocalDatas.getThreadInstance();
            System.out.println("model1 from " + Thread.currentThread() + "get data :" + datas.getName() + "age:" + datas.getAge());
        }
    }

    static class Model2 {
        public void get() {
            ThreadLocalDatas datas = ThreadLocalDatas.getThreadInstance();
            System.out.println("model2 from " + Thread.currentThread() + "get data :" + datas.getName() + "age:" + datas.getAge());
        }
    }

    static class ThreadLocalDatas {

        private ThreadLocalDatas() {
        }

        public static synchronized ThreadLocalDatas getThreadInstance(){
            ThreadLocalDatas datas = map.get();
            if (datas == null) {
                datas = new ThreadLocalDatas();
                map.set(datas);
            }
            return datas;
        }

        private static ThreadLocal<ThreadLocalDatas> map = new ThreadLocal<ThreadLocalDatas>();

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ThreadLocalDatas{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
