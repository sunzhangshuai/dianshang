package com.data.basics.inner;

public class Test {
    public static void main(String[] args) {
        //1. 创建成员内部类对象
        Person person = new Person();
        person.a = 100;
        Person.Eat eat = person.new Eat();
        eat.eatApple();
        //2. 创建静态成员内部类
        Person.Play play = new Person.Play();
        play.watchMovie();
        //3. 访问私有成员内部类的方法
        Person person1 = new Person();
        Face face = person1.getAmount();
        face.consume();
        //4. 访问局部内部类的方法
        Person per = new Person();
        Flowers flower = per.getFlowerName();
        flower.getName();
    }
}
