package com.data.basics.collection.set;

import com.data.basics.constructor.Son;
import java.util.TreeSet;

/**
 * TreeSetTest:
 * 二叉树：
 * @author sunchen
 * @date 2020/6/17 2:54 下午
 */
public class TreeSetTest {
    //1. comparable 和 comparator的区别？
    // comparable是一个内部较器，java.lang包下面的，类可以实现这个接口，实现类的多个对象可以自行进行比较。
    // comparator是一个外部比较器，java.util包下面的，使用场景，比如要比较的类没有实现comparable方法，需要外部比较
    // 器生成一个，或者说不想使用类自己的比较器
    // 优缺点：
    // comparator这个比较器，耦合性低，需要修改比较的方法时，不需要改被比较对象的那个类

    public static void main(String[] args) {
        //内部是一棵二叉树
        TreeSet<Son> treeSet = new TreeSet<Son>(new SonComparator());
        treeSet.add(new Son(1));
        treeSet.add(new Son(3));
        for (Son son : treeSet) {
            System.out.println(son.getA());
        }
    }
}
