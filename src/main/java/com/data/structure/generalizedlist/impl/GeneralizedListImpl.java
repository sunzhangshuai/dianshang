package com.data.structure.generalizedlist.impl;

import com.data.structure.generalizedlist.model.ElemTag;
import com.data.structure.generalizedlist.interfaces.GeneralizedList;
import com.data.structure.generalizedlist.model.GeneralizedNode;
import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.string.impl.SequenceDynamicStringImpl;

/**
 * GeneralizedList:
 *
 * @author sunchen
 * @date 2020/5/24 9:53 上午
 */
public class GeneralizedListImpl implements GeneralizedList<GeneralizedListImpl>, Cloneable {

    public GeneralizedNode<GeneralizedListImpl> node;

    static SequenceStackImpl stack = new SequenceStackImpl();

    public GeneralizedListImpl() {
    }

    public GeneralizedListImpl(char[] chars) {
        SequenceDynamicStringImpl left = new SequenceDynamicStringImpl(new char[]{'('});
        SequenceDynamicStringImpl right = new SequenceDynamicStringImpl(new char[]{')'});
        SequenceDynamicStringImpl blank = new SequenceDynamicStringImpl(new char[]{'(', ')'});
        //1.将字符数组转成字符串
        SequenceDynamicStringImpl string = new SequenceDynamicStringImpl(chars);
        if (string.strCompare(blank) == 0) {
            return;
        }
        node = new GeneralizedNode<GeneralizedListImpl>();
        // 创建原子节点
        if (string.index(1, left) != 1) {
            //创建结点
            node.tag = ElemTag.ATOM.code;
            node.atom = chars;
            return;
        }
        // 创建表结点
        // 2.把前后括号去掉，开始分割串
        string = string.subString(2, string.length - 2);
        int point = getBreakPoint(string);
        // 3.作为头的串
        SequenceDynamicStringImpl str1 = string.subString(1, point - 1);
        SequenceDynamicStringImpl str2 = blank;
        if (string.length != point - 1) {
            str2 = left.concat(string.subString(point + 1, string.length - point)).concat(right);
        }
        node.tag = ElemTag.LIST.code;
        node.hp = new GeneralizedListImpl(str1.toArray());
        node.tp = new GeneralizedListImpl(str2.toArray());
    }

    public int getBreakPoint(SequenceDynamicStringImpl string) {
        for (int i = 0; i < string.strLength(); i++) {
            if (string.hString[i] == '(') {
                stack.push("(");
            } else if (string.hString[i] == ')') {
                stack.pop();
            } else if (string.hString[i] == ',' && stack.stackEmpty()) {
                return i + 1;
            }
        }
        return string.strLength() + 1;
    }

    @Override
    public int gListLength() {
        int length = 0;
        GeneralizedNode<GeneralizedListImpl> start = node;
        while (start != null) {
            length++;
            start = start.tp.node;
        }
        return length;
    }

    @Override
    public int gListDepth() {
        GeneralizedNode<GeneralizedListImpl> start = node;
        if (start == null) {
            return 1;
        }
        if (start.tag == ElemTag.ATOM.code) {
            return 0;
        }
        int max = 0;
        while (start != null) {
            int deep = start.hp.gListDepth();
            if (deep > max) {
                max = deep;
            }
            start = start.tp.node;
        }
        return max + 1;
    }

    @Override
    public boolean gListEmpty() {
        return node == null;
    }

    @Override
    public GeneralizedListImpl getHead() {
        return node.hp;
    }

    @Override
    public GeneralizedListImpl getTail() {
        return node.tp;
    }

    /**
     * 往广义表的任何位置插入
     * (a,b,c)
     * @param e
     */
    @Override
    public void insertFirstGl(Object e) {
        GeneralizedListImpl newAdd = new GeneralizedListImpl();
        newAdd.node = new GeneralizedNode<GeneralizedListImpl>();
        newAdd.node.tag = ElemTag.ATOM.code;
        newAdd.node.atom = e;
        GeneralizedListImpl list = new GeneralizedListImpl();
        list.node = node;
        node = new GeneralizedNode<GeneralizedListImpl>();
        node.tag = ElemTag.LIST.code;
        node.hp = newAdd;
        node.tp = list;
    }

    /**
     * 在任意位置删除的一个原子结点，但是第一个元素不一定是一个原子结点？
     * 如果要删除广义表的第一个结点是表结点，此方法不支持
     * 修完善
     *
     *  内存模型（a，b，c），删除b。
     *  （a，b，c）                               (b,c)                                   (c)
     *  |--------|       |---------|         |----------|       |---------|          |----------|       |----------|        |------------|
     *  | node --------->| tag:1   |         | node  ---------> | tag:1   |          | node  ---------> | tag:1    |        | node：null |
     *  |        |       | tp  ------------->|          |       | tp  ------------->|          |        | tp：------------->|            |
     *  | 方法    |       | hp  ------|       | 方法     |       | hp  ------|       | 方法      |        | hp  --------|     | 方法        |
     *  |--------|       |---------| |       |----------|      |---------| |       |----------|         |--------- |  |     |------------|
     *                               |                                     |                                          |
     *                               |                                     |                                          |
     *                               |                                     |                                          |
     *                               |         a                           |          b                               |          c
     *                               |---> |--------|       |---------|    |-----> |--------|       |---------|       |-----> |--------|       |---------|
     *                                     | node --------->| tag:0   |            | node --------->| tag:0   |               | node --------->| tag:0   |
     *                                     |        |       | atom：a |            |        |       | atom：b |                |        |       | atom：c |
     *                                     | 方法    |      |         |            | 方法    |       |         |                | 方法   |       |         |
     *                                     |--------|      |---------|            |--------|       |---------|                |--------|       |---------|
     *
     * 最终效果：
     *   (a,c)                                   (c)
     *  |--------|       |---------|         |----------|       |----------|        |------------|
     *  | node --------->| tag:1   |         | node  ---------> | tag:1    |        | node：null |
     *  |        |       | tp  ------------->|          |       | tp：------------->|            |
     *  | 方法    |       | hp  ------|       | 方法     |       | hp  -------|      | 方法        |
     *  |--------|       |---------| |       |----------|      |----------| |      |------------|
     *                               |                                      |
     *                               |                                      |
     *                               |                                      |
     *                               |                                      |
     *                               |---> |--------|       |---------|     |----> |--------|       |---------|
     *                                     | node --------->| tag:0   |            | node --------->| tag:0   |
     *                                     |        |       | atom：a |            |        |       | atom：c |
     *                                     | 方法    |      |         |            |        |       |         |
     *                                     |--------|      |---------|            |--------|       |---------|
     *
     */
    @Override
    public Object deleteFirstGl() {

        // 在保证(b,c)不动的前提下，可做如下修改
        Object e = node.hp.node.atom;
        // 因为（b,c）所代表的空间是个list，只需要改变list的node
        node = node.tp.node;
        return e;
    }

    @Override
    public void traverseGl() {
        if (node == null) {
            return;
        }
        if (node.tag == ElemTag.ATOM.code) {
            visit(node);
        } else {
            node.hp.traverseGl();
            node.tp.traverseGl();
        }
    }

    public void visit(GeneralizedNode<GeneralizedListImpl> node) {
        System.out.println((char[]) node.atom);
    }

    @Override
    public GeneralizedListImpl clone() throws CloneNotSupportedException {
        GeneralizedListImpl clone = (GeneralizedListImpl) super.clone();
        if (node == null) {
            return clone;
        }
        clone.node = new GeneralizedNode<GeneralizedListImpl>();
        clone.node.tag = node.tag;
        if (node.tag == ElemTag.LIST.code) {
            clone.node.hp = node.hp.clone();
            clone.node.tp = node.tp.clone();
        } else {
            clone.node.atom = node.atom;
        }
        return clone;
    }

    @Override
    public char[] toArray() {
        SequenceDynamicStringImpl left = new SequenceDynamicStringImpl(new char[]{'('});
        SequenceDynamicStringImpl right = new SequenceDynamicStringImpl(new char[]{')'});
        SequenceDynamicStringImpl blank = new SequenceDynamicStringImpl(new char[]{'(', ')'});
        SequenceDynamicStringImpl comma = new SequenceDynamicStringImpl(new char[]{','});
        //把创建好的广义表，转成创建时，传入的字符数据
        if (node == null) {
            return blank.toArray();
        }
        if (node.tag == ElemTag.ATOM.code) {
            return (char[]) node.atom;
        }
        char[] str1 = node.hp.toArray();
        char[] str2 = node.tp.toArray();
        SequenceDynamicStringImpl string2 = new SequenceDynamicStringImpl(str2).subString(2, str2.length - 2);
        if (string2.length == 0) {
            return left.concat(new SequenceDynamicStringImpl(str1))
                    .concat(right).toArray();
        }
        return left.concat(new SequenceDynamicStringImpl(str1))
                .concat(comma)
                .concat(string2)
                .concat(right).toArray();
    }
}
