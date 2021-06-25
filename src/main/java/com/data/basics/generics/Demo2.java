package com.data.basics.generics;

/**
 * Demo2:
 * 泛型方法
 * @author sunchen
 * @date 2020/6/8 11:07 上午
 */
public class Demo2 {
    public<T> void disPlay(T name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.disPlay("sunchen");
        demo2.disPlay(1);
        demo2.disPlay(true);
    }
}
