package com.data.basics.inner;

public class Person {
    int a = 10;
    static int b = 20;

    /**
     * 1. 成员内部类，相当于成员方法，随着对象的存在而存在
     */
    public class Eat {
        int a = 20;
        public void eatApple() {
            System.out.println("内部的a:"+this.a);
            //成员内部类访问自己类的变量
            System.out.println("外部的a:"+Person.this.a);
            //成员内部类访问外部类的变量
            System.out.println("吃苹果");
            System.out.println(b);
        }
    }

    /**
     * 2. 静态成员内部类，随着类的存在而存在
     */
    public static class Play{
        public void watchMovie(){
            System.out.println(b);
            System.out.println("我在看电影哦");
        }
    }

    /**
     * 3. 私有的成员内部类
     */
    private class Amount implements Face{
        public void consume(){
            System.out.println("消费了很多钱");
        }
    }

    public Face getAmount(){
        return new Amount();
    }

    public Flowers getFlowerName(){
        final int a = 10;
        //4. 局部内部类
        class Mudan implements Flowers{
            @Override
            public void getName() {
                System.out.println("这是牡丹花");
                System.out.println(a);
            }
        }
        return new Mudan();
        //5. 匿名内部类
//        return new Flowers() {
//            @Override
//            public void getName() {
//                System.out.println("这是牡丹花");
//            }
//        };
    }
}
