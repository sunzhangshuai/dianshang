package com.data.basics.collection.list;

import java.util.LinkedList;

/**
 * LinkedListTest:
 *
 * @author sunchen
 * @date 2020/6/17 2:53 下午
 */
public class LinkedListTest {
    public static void main(String[] args) {
        //内部是双向链表，且有两个指针，分别指向链表的头和尾
        LinkedList<String> list = new LinkedList<String>();
        //尾插
        list.add("yy");
        //头插
        list.addFirst("yyy");
        //update
        list.set(1,"yyyyy");
        //往指定位置插一个，如果inde是最后一个，则尾插，如果不是，则在指定结点的前面插一个
        list.add(1,"88");
        //某元素第一次出现的下标
        list.indexOf("rr");
        //某元素最后一次出现的下标
        list.lastIndexOf("rr");
        //头删
        list.remove();
        list.poll();
        //尾删
        list.pop();

        //尾插
        list.offer("");
        //头插
        list.offerFirst("");

    }
}
