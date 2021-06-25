package com.data.basics.generics;

/**
 * Demo4:
 * 泛型接口用法二
 * @author sunchen
 * @date 2020/6/8 3:10 下午
 */
public interface Demo5<T> {
    /**
     * 展示
     */
    void display();
}
class DisPlay<T> implements Demo5<T>{

    @Override
    public void display() {

    }

    public static void main(String[] args) {
        DisPlay<String> ss = new DisPlay<String>();
    }
}


