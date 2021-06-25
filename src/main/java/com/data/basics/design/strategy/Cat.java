package com.data.basics.design.strategy;

/**
 * Cat:
 *
 * @author sunchen
 * @date 2020/7/9 12:58 下午
 */
public class Cat implements Comparable<Cat>{
    int weight;

    int age;

    public Cat(int weight, int age){
        this.weight = weight;
        this.age = age;
    }

    @Override
    public int compareTo(Cat o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", age=" + age +
                '}';
    }
}
