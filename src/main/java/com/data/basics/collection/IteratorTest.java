package com.data.basics.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * IteratorTest:
 *
 * @author sunchen
 * @date 2020/6/4 4:59 下午
 */
public class IteratorTest {
    public static void main(String[] args) {
        int a1 = test();
        System.out.println("hhhhh"+a1);
        ArrayList<String> list = new ArrayList<String>();
        HashSet<String> set = new HashSet<String>();
        set.add("1");
        Iterator<String> iterator1 = set.iterator();
        while (iterator1.hasNext()){
            iterator1.next();
        }
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String a = iterator.next();
            if ("1".equals(a)) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static int test(){
        int a = 10;
        try {
            System.out.println(a/0);
        }catch (Exception e) {
            a = 30;
            return a;
        }finally {
            a = 40;
            return a;
        }
    }
}
