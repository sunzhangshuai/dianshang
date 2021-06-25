package com.data.basics.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayListTest:
 *
 * @author sunchen
 * @date 2020/6/17 2:52 下午
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> aa = new ArrayList<Integer>();
        aa.add(1);
        aa.add(2);
        aa.add(3);
        Iterator<Integer> iterator1 = aa.iterator();
        while (iterator1.hasNext()){
            Integer next = iterator1.next();
            if (next == 3) {
                iterator1.remove();
            }
        }
//        //arraylist内部是可扩容的数组
//        /**
//         * 1. 创建集合，不指定大小
//         */
//        ArrayList<String> list = new ArrayList<String>();
//        /**
//         * 2. 首先判断是否需要扩容
//         *    首次添加，当前数组大小为空，则需要的数组大小为默认的10
//         * 3. 和当前的数组大小比较，如果当前的数组大小小于你要的容量大小，那么进行扩容
//         * 4. 扩容的规则：
//         *    先将容量扩大到原容量的1.5倍
//         *    如果计算出的新容量比要求的容量小，（比如第一次添加数据，扩容大小是10）
//         *    那么最终扩容大小为要求的容量
//         * 5. 创建一个新数组，将原数组的数据搬移到新数组中
//         */
//        list.add("yu");
//        //需要把从index开始的数据往后搬移一位
//        list.add(1, "ff");
//        ArrayList<String> coll = new ArrayList<String>();
//        //需要将coll里的数组复制到新扩容好的数组中的后面
//        list.addAll(coll);
//        ArrayList<String> colls = new ArrayList<String>();
//        colls.add("haha");
//        /**
//         * 2. 创建的时候，指定集合大小
//         */
//        ArrayList<String> arrayList = new ArrayList<String>(0);
//        /**
//         * 3. 指定了大小要为数组分配大小
//         */
//        ArrayList<String> list1 = new ArrayList<String>(colls);
//        /**
//         * 删除,迭代器遍历的过程中，不允许集合去做一些会改变集合长度的操作，比如删除和新增
//         */
//        list1.add("yyy");
//        list1.add("uuu");
//        //这样删除可以
//        for (int i = 0; i < list1.size(); i++) {
//            if (i == 1) {
//                list1.remove(i);
//            }
//        }
//        //这样删除，会报并发修改异常
//        for (String ss : list1) {
//            if ("uuu".equals(ss)) {
//                list1.remove(ss);
//            }
//        }
//        /**
//         * 迭代器，这样修改可以，因为用的是迭代器的删除，这个删除会更新expectedCount = modCount,所以下次不会报并发修改异常
//         */
//        Iterator<String> iterator = list1.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if ("uuu".equals(next)) {
//                iterator.remove();
//            }
//        }
    }
}
